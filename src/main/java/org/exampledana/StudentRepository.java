package org.exampledana;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentRepository {
    private static final Logger logger= LogManager.getLogger(StudentRepository.class);
    private List<Student> students;
    public static final int CURRENT_YEAR = 2024;


    public StudentRepository() {
        this.students= new ArrayList<>();
    }

    public boolean validateInputToAddStudent (String firstName, String lastName, int dayOfBirth, int monthOfBirth, int yearOfBirth, String gender, String id) {
        try{
            if(firstName==null||firstName.isBlank()) {
                throw new IllegalArgumentException("User typed: "+firstName+" for first name. First name can not be an empty string!");
            }
            if(lastName==null||lastName.isBlank()) {
                throw new IllegalArgumentException("User typed: "+lastName+" for last name. Last name can not be an empty string!");
            }
            if ((monthOfBirth< 1) || (monthOfBirth> 12)) {
                throw new IllegalArgumentException("Invalid date of birth! Month of birth must be a number between 1 and 12."+
                        " User typed: "+monthOfBirth);
            }
            switch (monthOfBirth) {
                case 1, 3, 5, 7, 8, 10, 12 -> {
                    if ((dayOfBirth < 1) || (dayOfBirth > 31)) {
                        throw new IllegalArgumentException("Invalid date of birth!" +
                                " Day of birth must be must be a number between 1 and 31. User typed: "+dayOfBirth);
                    }
                }
                case 2 -> {
                    if ((dayOfBirth < 1) || (dayOfBirth > 29)) {
                        throw new IllegalArgumentException("Invalid date of birth!" +
                                " Day of birth must be must be a number between 1 and 29. User typed: "+dayOfBirth);
                    }
                }
                default -> {
                    if ((dayOfBirth < 1) || (dayOfBirth > 30)) {
                        throw new IllegalArgumentException("Invalid date of birth!" +
                                " Day of birth must be must be a number between 1 and 30. User typed: "+dayOfBirth);
                    }
                }
            }
            if ((yearOfBirth< 1900) || (yearOfBirth> StudentRepository.CURRENT_YEAR-18)) {
                throw new IllegalArgumentException("Invalid date of birth! User typed: "+yearOfBirth+
                        ". Year of birth must be an year between 1900 and "+(StudentRepository.CURRENT_YEAR-18));
            }
            if (gender.isEmpty() ||(!(""+gender.charAt(0)).equalsIgnoreCase("m")&&!(""+gender.charAt(0)).equalsIgnoreCase("f"))) {
                throw new IllegalArgumentException("Gender should be male or female (or M and F)- both upper and lower case are accepted. User typed: "+ gender);
            }
            if((!id.matches("\\d+"))||id.length()<11) {
                throw new IllegalArgumentException("Id must be a number with more then 11 digits (13 digits for romanian students). User typed: "+id);
            }
            if(!students.stream().noneMatch(student -> student.getId().equals(id))){
                throw new IllegalArgumentException("This student is already registered in the repository. User typed: "+id);
            }
            return true;
        } catch (IllegalArgumentException exception) {
            logger.error(exception);
        }
        return false;
    }
    public boolean addStudent (String firstName, String lastName, int dayOfBirth, int monthOfBirth, int yearOfBirth, String gender, String id) {
        if(validateInputToAddStudent(firstName,lastName,dayOfBirth,monthOfBirth, yearOfBirth, gender,id)) {
            return (students.add(new Student(firstName, lastName, dayOfBirth, monthOfBirth, yearOfBirth, gender, id)));
        }
        return false;
    }
    public boolean validateInputAndFindStudentById(String id){
        try {
            if(id==null||id.isBlank()) {
                throw new IllegalArgumentException("Invalid ID to delete! User typed:" + id);
            } else if ( students.stream().noneMatch(student -> student.getId().equals(id))) {
                throw new IllegalArgumentException("Invalid ID to delete! Student does not exist! User typed:" + id);
            } else {
                return true;
            }
        } catch (IllegalArgumentException exception){
            logger.error(exception);
        }
        return false;
    }
    public boolean deleteStudent(String id){
        if (validateInputAndFindStudentById(id)) {
            students.removeIf(student -> student.getId().equals(id));
            return true;
        }
        return false;
    }

    public boolean inputAgeIsCorrect(String ageString){
        try {
            int age = Integer.parseInt(ageString);
            if(age <0) throw new IllegalArgumentException("Invalid input! Age can not be less than 0!! Try again! User typed:"+ageString);
            return true;
        } catch (NumberFormatException exception) {
            logger.error(exception+" Invalid input! Age must be a number! Try again!");
        }  catch (IllegalArgumentException exception){
            logger.error(exception);
        }
        return false;
    }
    public boolean getStudentsWithTheSameAge(String ageString){
        if(inputAgeIsCorrect(ageString)) {
            int finalAge = Integer.parseInt(ageString);
            students.stream().filter(student -> student.getAge() == finalAge).forEach(System.out::println);
            return true;
        }
      return false;
    }

    public boolean validateAndListStudentsOrdered(String comparator){
        boolean informationIsCorrect= true;
            try {
                if (comparator.equalsIgnoreCase("name")) {
                    listStudentsOrderedByLastName();
                } else if (comparator.equalsIgnoreCase("date")) {
                    listStudentsOrderedByBirthDate();
                } else {
                    throw new IllegalArgumentException("Invalid option! (Input should be 'name' or 'date') Try again! User input= "+comparator);
                }
            } catch (IllegalArgumentException exception){
                logger.error(exception);
                informationIsCorrect= false;
            }
       return informationIsCorrect;
    }

    public void listStudentsOrderedByLastName(){
     students.sort((s1,s2)->s1.getLastName().compareToIgnoreCase(s2.getLastName()));
     printStudents();
    }
    public void listStudentsOrderedByBirthDate(){
    students.sort(Comparator.comparing(Student::getYearOfBirth).thenComparing(Student::getMonthOfBirth).thenComparing(Student::getDayOfBirth));
    printStudents();
}

    public void printStudents(){
        System.out.println("STUDENT REPOSITORY:");
       students.forEach(System.out::println);
    }

    public List<Student> getStudents(){
        List<Student> copy= new ArrayList<>();
        copy.addAll(students);
        return  copy;
    }
}

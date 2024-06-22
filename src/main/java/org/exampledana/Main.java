package org.exampledana;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    private static final Logger logger= LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        StudentRepository studentRepository= new StudentRepository();
        Scanner scan= new Scanner(System.in);
        Options option=null;
        String input= null;
        do {
            printMenu();
            try {
                input= scan.nextLine();
                option = Options.valueOf(input.toUpperCase());
                switch (option){
                    case ADD -> {
                        boolean informationIsCorrect= false;
                        do {
                            System.out.println("You want to add a new student. Please enter the required information!");
                            System.out.println("Enter student first name: ");
                            String firstName = scan.nextLine();
                            System.out.println("Enter student last name: ");
                            String lastName = scan.nextLine();
                            boolean correctDateOfBirth = false;
                            int dayOfBirth = 0, monthOfBirth = 0, yearOfBirth = 0;
                            do {
                                System.out.println("Insert student birth date (dd.mm.yyyy): ");
                                String birthDate= scan.nextLine().replace(" ", "");
                                try {
                                    dayOfBirth = Integer.parseInt(birthDate.substring(0,2));
                                    monthOfBirth=Integer.parseInt(birthDate.substring(3,5));
                                    yearOfBirth=Integer.parseInt(birthDate.substring(6));
                                    correctDateOfBirth = true;
                                } catch (NumberFormatException|StringIndexOutOfBoundsException exception) {
                                    System.out.println("Invalid format! Date of birth must observe the format: dd.mm.yyyy! Try again!");
                                }
                            } while (correctDateOfBirth == false);
                            System.out.println("Enter student gender: male(m) or female(f): ");
                            String gender = scan.nextLine();
                            System.out.println("Enter student ID(CNP): ");
                            String id = scan.nextLine();
                            informationIsCorrect =studentRepository.addStudent(
                                    firstName, lastName, dayOfBirth, monthOfBirth, yearOfBirth, gender, id);
                            System.out.println("STUDENT REPOSITORY:");
                            studentRepository.printStudents();
                        } while (!informationIsCorrect);
                    }
                    case DELETE -> {
                        boolean informationIsCorrect= false;
                        do{
                            System.out.println("You want to delete an existing student from this repository.\n" +
                                    "Please enter the ID of the student that you want to delete from repository:");
                            String id = scan.nextLine();
                            informationIsCorrect=studentRepository.deleteStudent(id);
                        } while (!informationIsCorrect);
                        studentRepository.printStudents();
                    }
                    case LIST -> {
                        boolean informationIsCorrect= false;
                        do{
                            System.out.println("You want to list all students");
                            System.out.println("Type 'name' if you want the list of students ordered by last name");
                            System.out.println("Type 'date' if you want the list of students ordered by birth date");
                            String comparator=scan.nextLine();
                            informationIsCorrect=studentRepository.validateAndListStudentsOrdered(comparator);
                        } while (!informationIsCorrect);
                    }
                    case RETRIEVE -> {
                        boolean informationIsCorrect= false;
                        do {
                            System.out.println("You want to list all students with a certain age. Type the age of students: ");
                            String ageString = scan.nextLine();
                            informationIsCorrect=studentRepository.retrieveStudentsWithTheSameAge(ageString);
                        } while (!informationIsCorrect);
                    }
                }
            } catch (IllegalArgumentException exception) {
                System.out.println("Invalid option! Try again!");
                logger.error("User typed: "+input, exception);
            }
        } while (option!=Options.QUIT);
    }

    public static void printMenu(){
        System.out.println("""
        Student repository supports the following operations:
            - ADD a new student (type "ADD" if you want to add a new student);
            - DELETE student by ID - identifier (type "DELETE" if you want to delete a student);
            - GET all students with a certain age  (type "GET" if you want to retrieve all students with a certain age);
            - LIST students order by Last Name or Birth Date  (type "LIST" if you want to list students);
            - QUIT - type "QUIT" if you want to exit.
        Enter your option: 
        """);
    }
}

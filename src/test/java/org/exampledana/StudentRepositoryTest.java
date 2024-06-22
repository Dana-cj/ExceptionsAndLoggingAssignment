package org.exampledana;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StudentRepositoryTest {
    @Test
    public void addOptionWorksWhenInformationIsCorrect() {
        StudentRepository studentRepositoryTest= new StudentRepository();
        studentRepositoryTest.addStudent("Ion", "Pop", 05, 10, 2000, "m", "1001005412173");
        Assertions.assertTrue(studentRepositoryTest.getStudents().size()==1);
    }
    @Test
    public void validationsForAddOptionWhenFirstNameIsEmptyOrBlank() {
        StudentRepository studentRepositoryTest= new StudentRepository();
        studentRepositoryTest.addStudent("", "Pop", 05, 10, 2000, "m", "1201005412173");
        Assertions.assertTrue(studentRepositoryTest.getStudents().size()==0);
        studentRepositoryTest.addStudent(" ", "Pop", 05, 10, 2000, "m", "1201005412173");
        Assertions.assertTrue(studentRepositoryTest.getStudents().size()==0);
    }
    @Test
    public void validationsForAddOptionWhenLastNameIsEmptyOrBlank() {
        StudentRepository studentRepositoryTest= new StudentRepository();
        studentRepositoryTest.addStudent("Ion", "", 05, 10, 2000, "m", "1001005412173");
        Assertions.assertEquals(0, studentRepositoryTest.getStudents().size());
        studentRepositoryTest.addStudent("Ion", " ", 05, 10, 2000, "m", "1001005412173");
        Assertions.assertEquals(0, studentRepositoryTest.getStudents().size());
    }
    @Test
    public void validationsForAddOptionWhenDateOfBirthIncorrect() {
        StudentRepository studentRepositoryTest= new StudentRepository();
        studentRepositoryTest.addStudent("Ion", "Pop", 5, 10, 1899, "m", "1001005412173");
        Assertions.assertEquals(0, studentRepositoryTest.getStudents().size());
        studentRepositoryTest.addStudent("Ion", "Pop", 5, 10, 2007, "m", "1001005412173");
        Assertions.assertEquals(0, studentRepositoryTest.getStudents().size());
    }
    @Test
    public void validationsForAddOptionWhenGenderIsIncorrect() {
        StudentRepository studentRepositoryTest= new StudentRepository();
        studentRepositoryTest.addStudent("Ion", "Pop", 05, 10, 2000, "k", "1001005412173");
        Assertions.assertEquals(0, studentRepositoryTest.getStudents().size());
        studentRepositoryTest.addStudent("Ion", "Pop", 05, 10, 2000, "", "1001005412173");
        Assertions.assertEquals(0, studentRepositoryTest.getStudents().size());
    }
    @Test
    public void validationsForAddOptionWhenIdIsIncorrect() {
        StudentRepository studentRepositoryTest= new StudentRepository();
        studentRepositoryTest.addStudent("Ion", "Pop", 05, 10, 2000, "m", "ion");
        Assertions.assertEquals(0, studentRepositoryTest.getStudents().size());
        studentRepositoryTest.addStudent("Ion", "Pop", 05, 10, 2000, "m", "");
        Assertions.assertEquals(0, studentRepositoryTest.getStudents().size());
        studentRepositoryTest.addStudent("Ion", "Pop", 05, 10, 2000, "m", "565144");
        Assertions.assertEquals(0, studentRepositoryTest.getStudents().size());
    }

    @Test
    public void deleteOptionWorksWhenIdIsCorrect() {
        StudentRepository studentRepositoryTest= new StudentRepository();
        studentRepositoryTest.addStudent("Ion", "Pop", 05, 10, 2000, "m", "1001005412173");
        studentRepositoryTest.printStudents();
        System.out.println("-".repeat(15));
        studentRepositoryTest.deleteStudent("1001005412173");
        Assertions.assertEquals(0, studentRepositoryTest.getStudents().size());
        studentRepositoryTest.printStudents();
    }
    @Test
    public void deleteOptionWorksWhenIdIsIncorrect() {
        StudentRepository studentRepositoryTest= new StudentRepository();
        studentRepositoryTest.addStudent("Ion", "Pop", 05, 10, 2000, "m", "1001005412173");
        studentRepositoryTest.printStudents();
        System.out.println("-".repeat(15));
        studentRepositoryTest.deleteStudent("");
        Assertions.assertEquals(1, studentRepositoryTest.getStudents().size());
        studentRepositoryTest.deleteStudent("2001005412173");
        Assertions.assertEquals(1, studentRepositoryTest.getStudents().size());
        studentRepositoryTest.printStudents();
    }
    @Test
    public void listOptionWorksWhenInputIsName() {
        StudentRepository studentRepositoryTest= new StudentRepository();
        studentRepositoryTest.addStudent("Ion", "Pop", 05, 10, 2000, "m", "1001005412173");
        studentRepositoryTest.addStudent("Ion", "David", 05, 10, 1980, "m", "2001005412173");
        studentRepositoryTest.printStudents();
        System.out.println("-".repeat(15));
        Assertions.assertTrue(studentRepositoryTest.validateAndListStudentsOrdered("name"));
    }
    @Test
    public void listOptionWorksWhenInputIsDate() {
        StudentRepository studentRepositoryTest= new StudentRepository();
        studentRepositoryTest.addStudent("Ion", "Pop", 05, 10, 2000, "m", "1001005412173");
        studentRepositoryTest.addStudent("Ion", "David", 05, 10, 1980, "m", "2001005412173");
        studentRepositoryTest.printStudents();
        System.out.println("-".repeat(15));
        Assertions.assertTrue(studentRepositoryTest.validateAndListStudentsOrdered("name"));
    }

    @Test
    public void listOptionWorksWhenInputIsIncorrect() {
        StudentRepository studentRepositoryTest= new StudentRepository();
        studentRepositoryTest.addStudent("Ion", "Pop", 05, 10, 2000, "m", "1001005412173");
        studentRepositoryTest.addStudent("Ion", "David", 05, 10, 1980, "m", "2001005412173");
        studentRepositoryTest.printStudents();
        System.out.println("-".repeat(15));
        Assertions.assertFalse(studentRepositoryTest.validateAndListStudentsOrdered(""));
        Assertions.assertFalse(studentRepositoryTest.validateAndListStudentsOrdered(" "));
        Assertions.assertFalse(studentRepositoryTest.validateAndListStudentsOrdered("birth"));
    }
    @Test
    public void getOptionWorksWhenInputIsCorrect() {
        StudentRepository studentRepositoryTest= new StudentRepository();
        studentRepositoryTest.addStudent("Ion", "Pop", 05, 10, 2000, "m", "1001005412173");
        studentRepositoryTest.addStudent("Ion", "David", 05, 10, 1980, "m", "2001005412173");
        studentRepositoryTest.printStudents();
        System.out.println("-".repeat(15));
        Assertions.assertTrue(studentRepositoryTest.retrieveStudentsWithTheSameAge("24"));
    }
    @Test
    public void getOptionWorksWhenInputIsEmptyOrNaN() {
        StudentRepository studentRepositoryTest= new StudentRepository();
        studentRepositoryTest.addStudent("Ion", "Pop", 05, 10, 2000, "m", "1001005412173");
        studentRepositoryTest.addStudent("Ion", "David", 05, 10, 1980, "m", "2001005412173");
        studentRepositoryTest.printStudents();
        System.out.println("-".repeat(15));
        Assertions.assertFalse(studentRepositoryTest.retrieveStudentsWithTheSameAge(""));
        Assertions.assertFalse(studentRepositoryTest.retrieveStudentsWithTheSameAge("nineteen"));
    }
    @Test
    public void getOptionWorksWhenInputIsNegative() {
        StudentRepository studentRepositoryTest= new StudentRepository();
        studentRepositoryTest.addStudent("Ion", "Pop", 05, 10, 2000, "m", "1001005412173");
        studentRepositoryTest.addStudent("Ion", "David", 05, 10, 1980, "m", "2001005412173");
        studentRepositoryTest.printStudents();
        System.out.println("-".repeat(15));
        Assertions.assertFalse(studentRepositoryTest.retrieveStudentsWithTheSameAge("-15"));
    }
}

package org.exampledana;

public class Student {
    private String firstName;
    private String lastName;
    private int dayOfBirth;
    private int monthOfBirth;
    private int yearOfBirth;
    private Gender gender;
    private String id;

    public Student(String firstName, String lastName, int dayOfBirth, int monthOfBirth, int yearOfBirth, String gender, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dayOfBirth = dayOfBirth;
        this.monthOfBirth=monthOfBirth;
        this.yearOfBirth=yearOfBirth;
        this.gender = Gender.valueOf(gender.toUpperCase());
        this.id = id;
    }
    public int getAge(){
      return (StudentRepository.CURRENT_YEAR-yearOfBirth);
    }
    public String getLastName() {
        return lastName;
    }
    public int getDayOfBirth() {
        return dayOfBirth;
    }
    public int getMonthOfBirth() {
        return monthOfBirth;
    }
    public int getYearOfBirth() {
        return yearOfBirth;
    }
    public String getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dayOfBirth+"."+monthOfBirth+"."+yearOfBirth + '\'' +
                ", gender=" + gender +
                ", id='" + id + '\'' +
                '}';
    }
}

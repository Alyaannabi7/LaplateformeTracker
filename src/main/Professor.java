package main;

public class Professor {
    private String firstName;
    private String lastName;
    private int age;
    private String course;

    public Professor(String firstName, String lastName, int age, String course){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.course = course;
    }

    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public int getAge() {return age;}
    public String getCourse() {return course;}

}

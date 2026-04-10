package main;

public class Student {
    private String first_name;
    private String last_name;
    private int age;
    private double grade;
    private String course;

    public Student(String first_name, String last_name, int age, double grade, String course){
        
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.grade = grade;
        this.course = course;

    }

    public String getFirst_name() {return first_name;}
    public String getLast_name() {return last_name;}
    public int getAge() {return age;}
    public double getGrade() {return grade;}
    public String getCourse() {return course;}
}

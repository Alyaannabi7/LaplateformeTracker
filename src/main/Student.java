package main;

public class Student {
    private String firstName;
    private String lastName;
    private int age;
    private double grade;
    private String course;

    public Student(String firstName, String lastName, int age, double grade, String course){
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.grade = grade;
        this.course = course;

    }

    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public int getAge() {return age;}
    public double getGrade() {return grade;}
    public String getCourse() {return course;}

    public void setGrade(double grade){
        if (grade >= 0 && grade <=20) {
            this.grade = grade;
        }
    }

    // a modifier quand on aura l'interface
    @Override
    public String toString() {
        return firstName + " " + lastName +
                "Age: " + age +
                " Matiere: " + course +
                " Note: " + grade;
    }
}

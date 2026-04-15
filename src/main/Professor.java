package main;

public class Professor {
    private int professorID;
    private String firstName;
    private String lastName;
    private int age;
    private String course;

    public Professor(int professorID, String firstName, String lastName, int age, String course){

        this.professorID = professorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.course = course;
    }

    public int getProfessorID() {return professorID;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public int getAge() {return age;}
    public String getCourse() {return course;}

    // a modifier quand on aura l'interface
    @Override
    public String toString() {
        return firstName + " " +
                lastName +
                "Age: " + age +
                " Matiere: " + course;
    }
}

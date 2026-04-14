package main;

public class Professor {
    private String firstName;
    private String lastName;
    private int age;
    private String profession;

    public Professor(String firstName, String lastName, int age, String profession){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.profession = profession;
    }

    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public int getAge() {return age;}
    public String getProfession() {return profession;}

    // a modifier quand on aura l'interface
    @Override
    public String toString() {
        return firstName + " " +
                lastName +
                "Age: " + age +
                " Matiere: " + profession;
    }
}

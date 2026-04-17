package main.java;

import main.ProfessorManager;
import main.StudentManager;
import main.Student;
import main.Professor;

public class main {
    public static void main(String[]args) {
        StudentManager student = new StudentManager();
        ProfessorManager prof = new ProfessorManager();

        prof.addProfessor(new Professor(1,"el", "papi", 25, "TE"));
        student.addStudent(new Student(1,"Skibidi", "Sigma", 15, 10, "Francais"));
        student.addStudent(new Student(2,"sbf", "fff", 18, 15.5, "Math"));

        prof.showAllProfessors();
        student.showAllStudents();
        System.out.println("Moyenne generale de la classe: " + student.averageGrade());
        System.out.println("Moyenne d'age de la classe: " + student.averageAge());
    }
}

package main.java;

import main.CourseManager;
import main.GradeManager;
import main.Student;
import main.Professor;

public class Main {
    public static void main(String[]args) {
        GradeManager grade = new GradeManager();
        CourseManager prof = new CourseManager();

        prof.addProfessor(new Professor("el", "papi", 25, "TE"));
        grade.addStudent(new Student("Skibidi", "Sigma", 15, 10, "Francais"));
        grade.addStudent(new Student("Yaniss", "Aouri", 15, 15.5, "Math"));

        prof.showAllProfessors();
        grade.showAllStudents();
        System.out.println("Moyenne generale de la classe: " + grade.averageGrade());
    }
}

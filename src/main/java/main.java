package main.java;

import main.GradeManager;
import main.Student;

public class Main {
    public static void main(String[]args) {
        GradeManager manager = new GradeManager();

        manager.addStudent(new Student("Yaniss", "Aouri", 15, 10, "Francais"));
        manager.addStudent(new Student("Yaniss", "Aouri", 15, 15.5, "Math"));

        manager.showAllStudents();
        System.out.println("Moyenne generale: " + manager.averageGrade());
    }
}

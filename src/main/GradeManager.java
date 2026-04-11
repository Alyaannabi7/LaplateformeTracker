package main;

import java.util.ArrayList;

public class GradeManager {
    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }
    public void showAllStudents(){
        for (Student s : students) {
            System.out.println(s);
        }
    }
    public double averageGrade() {
        if (students.isEmpty())
            return 0;
        double sum = 0;
        for (Student s : students) {
            sum += s.getGrade();
        }
        return sum /students.size();
    }
}

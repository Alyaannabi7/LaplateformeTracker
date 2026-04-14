package main;

import java.util.ArrayList;

public class CourseManager {
    private ArrayList<Professor> professors = new ArrayList<>();
    
    public void addProfessor(Professor professor) {
        professors.add(professor);
    }
    public void showAllProfessors(){
        for (Professor p : professors) {
            System.out.println(p);
        }
    }
}
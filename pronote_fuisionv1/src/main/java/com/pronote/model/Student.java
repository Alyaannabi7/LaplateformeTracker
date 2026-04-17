package com.pronote.model;

public class Student {
    public final String lastName;
    public final String firstName;
    public final String age;
    public final String filiere;
    public final String grade;

    public Student(String lastName, String firstName, String age, String filiere, String grade) {
        this.lastName  = lastName;
        this.firstName = firstName;
        this.age       = age;
        this.filiere   = filiere;
        this.grade     = grade;
    }
}
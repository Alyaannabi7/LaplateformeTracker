package com.pronote.model;

public class Professor {
    public final String firstName;
    public final String lastName;
    public final String email;
    public final String password;
    public final String subject;

    public Professor(String firstName, String lastName, String email, String password, String subject) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.email     = email;
        this.password  = password;
        this.subject   = subject;
    }
}
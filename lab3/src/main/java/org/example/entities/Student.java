package org.example.entities;

import org.example.annotations.*;
/**
 * Represents a student with validation annotations.
 */
public class Student {

    @NotNull
    private String studentID;

    @StringLength(min = 3, max = 15)
    private String name;

    @MinValue(3)
    @MaxValue(30)
    private Integer credits;

    public Student(String studentID, String name, Integer credits) {
        this.studentID = studentID;
        this.name = name;
        this.credits = credits;
    }
}


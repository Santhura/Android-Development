package com.example.dylangijsbertsen.homework;

/**
 * Created by Dylan Gijsbertsen on 18-9-2015.
 */
public class Assignment {
    private long id;
    private String assignment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    @Override
    public String toString() {
        return assignment;
    }
}

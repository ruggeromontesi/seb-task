package com.seb.task.exceptions;

public class InvalidAgeException extends  RuntimeException{
    private static final long serialVersionUID = 1L;

    private int age;

    public InvalidAgeException(String exception, int age) {
        super(exception);
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}

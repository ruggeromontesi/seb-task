package com.seb.task.dto;

public class CustomerAnswersDto {
    private  int age;

    private boolean student;

    private int income;

    public CustomerAnswersDto() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }
}

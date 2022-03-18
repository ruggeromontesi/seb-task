package com.seb.task.dto;

public class CustomerAnswersDto {
   private Integer age;

   private boolean student;

   private Integer income;

   public CustomerAnswersDto() {
   }

   public Integer getAge() {
      return age;
   }

   public void setAge(Integer age) {
      this.age = age;
   }

   public boolean isStudent() {
      return student;
   }

   public void setStudent(boolean student) {
      this.student = student;
   }

   public Integer getIncome() {
      return income;
   }

   public void setIncome(Integer income) {
      this.income = income;
   }
}

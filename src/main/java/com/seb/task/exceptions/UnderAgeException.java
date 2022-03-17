package com.seb.task.exceptions;

public class UnderAgeException  extends  RuntimeException{
   private int age;

   public UnderAgeException(String exception, int age) {
      super(exception);
      this.age = age;
   }

   public int getAge() {
      return age;
   }
}

package com.seb.task.exceptions;

public class IsNotStudentException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public IsNotStudentException(String exception) {
      super(exception);
   }
}

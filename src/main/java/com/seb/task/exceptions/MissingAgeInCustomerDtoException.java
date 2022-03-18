package com.seb.task.exceptions;

public class MissingAgeInCustomerDtoException extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public MissingAgeInCustomerDtoException(String message) {
      super(message);
   }
}

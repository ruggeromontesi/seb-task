package com.seb.task.exceptions;


public class MissingIncomeInCustomerDtoException extends RuntimeException{
   private static final long serialVersionUID = 1L;

   public MissingIncomeInCustomerDtoException(String message) {
      super(message);
   }
}

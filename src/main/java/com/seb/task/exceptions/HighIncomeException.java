package com.seb.task.exceptions;

public class HighIncomeException extends RuntimeException {
   private static final long serialVersionUID = 1L;

   private int income;

   public HighIncomeException(String message, int income) {
      super(message);
      this.income = income;

   }

   public int getIncome() {
      return income;
   }
}

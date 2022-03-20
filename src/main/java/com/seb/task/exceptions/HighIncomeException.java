package com.seb.task.exceptions;

public class HighIncomeException extends RuntimeException {
   private static final long serialVersionUID = 1L;

   private double income;

   public HighIncomeException(String message, double income) {
      super(message);
      this.income = income;

   }

   public double getIncome() {
      return income;
   }

}

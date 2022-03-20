package com.seb.task.exceptions;


public class ZeroIncomeException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   private double income;

   public ZeroIncomeException(String message, double income) {
      super(message);
      this.income = income;

   }

   public double getIncome() {
      return income;
   }
}

package com.seb.task.exceptions;

public class MiddleIncomeException  extends  RuntimeException {
   private static final long serialVersionUID = 1L;

   private double income;

   public MiddleIncomeException(String message, double income) {
      super(message);
      this.income = income;

   }

   public double getIncome() {
      return income;
   }
}

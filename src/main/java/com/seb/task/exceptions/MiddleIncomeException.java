package com.seb.task.exceptions;


public class MiddleIncomeException  extends  RuntimeException {
   private static final long serialVersionUID = 1L;

   private int income;

   public MiddleIncomeException (String message, int income) {
      super(message);
      this.income = income;

   }

   public int getIncome() {
      return income;
   }
}

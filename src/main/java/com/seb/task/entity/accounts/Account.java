package com.seb.task.entity.accounts;

public class Account {

   protected AccountType accountType;

   public Account(AccountType accountType) {
      this.accountType = accountType;
   }

   public AccountType getAccountType() {
      return accountType;
   }
}
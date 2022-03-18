package com.seb.task.entity.accounts;

public class Account {

   protected AccountType accountType;

   public AccountType getAccountType() {
      return accountType;
   }

   public static Account getAccountFromAccountType(AccountType accountType) {
      Account newAccount = null;

      if (accountType == AccountType.CURRENT_ACCOUNT) {
         newAccount = new CurrentAccount();
      }
      if (accountType == AccountType.CURRENT_ACCOUNT_PLUS) {
         newAccount = new CurrentAccountPlus();
      }

      if (accountType == AccountType.CURRENT_ACCOUNT_PLUS) {
         newAccount = new CurrentAccountPlus();
      }

      if (accountType == AccountType.JUNIOR_SAVER_ACCOUNT) {
         newAccount = new JuniorSaverAccount();
      }

      return newAccount;

   }
}

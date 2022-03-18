package com.seb.task.service;

import com.seb.task.dto.CustomerAnswersDto;
import com.seb.task.entity.bundle.BundleType;
import com.seb.task.entity.product.accounts.AccountType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RuleCheckerServiceTest {

   private RuleCheckerService ruleCheckerService = new RuleCheckerService();

   @Test
   public void isCurrentAccountAllowedTest(){
      CustomerAnswersDto dto = new CustomerAnswersDto();
      dto.setAge(18);
      dto.setIncome(2000);
      Assertions.assertTrue(ruleCheckerService.isCurrentAccountAllowed(dto));
      dto.setIncome(0);
      //Assertions.assertFalse(ruleCheckerService.isCurrentAccountAllowed(dto));
      dto.setAge(17);
      dto.setIncome(2000);
      //Assertions.assertFalse(ruleCheckerService.isCurrentAccountAllowed(dto));
   }

   @Test
   public  void isCurrentAccountPlusAllowedTest() {
      CustomerAnswersDto dto = new CustomerAnswersDto();
      dto.setAge(18);
      dto.setIncome(41000);
      Assertions.assertTrue(ruleCheckerService.isCurrentAccountPlusAllowed(dto));
      dto.setIncome(15000);
      Assertions.assertFalse(ruleCheckerService.isCurrentAccountPlusAllowed(dto));
   }

   @Test
   public void isJuniorSaverAccountAllowedTest() {
      CustomerAnswersDto dto = new CustomerAnswersDto();
      dto.setAge(18);
      dto.setIncome(41000);
      Assertions.assertFalse(ruleCheckerService.isJuniorSaverAccountAllowed(dto));
      dto.setAge(17);
      Assertions.assertTrue(ruleCheckerService.isJuniorSaverAccountAllowed(dto));
   }

   @Test
   public void isStudentAccountAllowedTest() {
      CustomerAnswersDto dto = new CustomerAnswersDto();
      dto.setAge(18);
      dto.setIncome(41000);
      Assertions.assertFalse(ruleCheckerService.isStudentAccountAllowed(dto));
      dto.setStudent(true);
      Assertions.assertTrue(ruleCheckerService.isStudentAccountAllowed(dto));

   }

   @Test
   public void isDebitCardAllowedTest() {
      for(BundleType bundleType : BundleType.values()) {
         if(bundleType == BundleType.JUNIOR_SAVER) {
            Assertions.assertFalse(ruleCheckerService.isDebitCardAllowed(bundleType));
         } else {
            Assertions.assertTrue(ruleCheckerService.isDebitCardAllowed(bundleType));
         }
      }
   }

   @Test
   public void isCreditCardAllowed() {
      CustomerAnswersDto dto = new CustomerAnswersDto();
      dto.setAge(18);
      dto.setIncome(41000);
      Assertions.assertTrue(ruleCheckerService.isCreditCardAllowed(dto));
      dto.setIncome(1000);
      Assertions.assertFalse(ruleCheckerService.isCreditCardAllowed(dto));
      dto.setIncome(21000);
      Assertions.assertTrue(ruleCheckerService.isCreditCardAllowed(dto));
   }

   @Test
   public void isGoldCreditCardAllowed() {
      CustomerAnswersDto dto = new CustomerAnswersDto();
      dto.setAge(18);
      dto.setIncome(41000);
      Assertions.assertTrue(ruleCheckerService.isGoldCreditCardAllowed(dto));
      dto.setIncome(10000);
      Assertions.assertFalse(ruleCheckerService.isGoldCreditCardAllowed(dto));
      dto.setIncome(1000);
      Assertions.assertFalse(ruleCheckerService.isGoldCreditCardAllowed(dto));
   }

   @Test
   public void isThisTypeOfAccountAllowedTest() {
      CustomerAnswersDto dto = new CustomerAnswersDto();
      dto.setAge(18);
      dto.setIncome(41000);
      AccountType accountType = AccountType.JUNIOR_SAVER_ACCOUNT;
      //Assertions.assertFalse(ruleCheckerService.isThisTypeOfAccountAllowed(newAccountType,dto));
      dto.setAge(17);
      Assertions.assertTrue(ruleCheckerService.isThisTypeOfAccountAllowed(accountType,dto));
      accountType = AccountType.CURRENT_ACCOUNT_PLUS;
      //Assertions.assertFalse(ruleCheckerService.isThisTypeOfAccountAllowed(newAccountType,dto));
      dto.setAge(28);
      Assertions.assertTrue(ruleCheckerService.isThisTypeOfAccountAllowed(accountType,dto));
      dto.setIncome(20000);
      //Assertions.assertFalse(ruleCheckerService.isThisTypeOfAccountAllowed(newAccountType,dto));
   }

}

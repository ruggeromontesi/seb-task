package com.seb.task.service;

import com.seb.task.dto.CustomerAnswersDto;
import com.seb.task.entity.bundle.BundleType;
import com.seb.task.entity.product.accounts.AccountType;
import com.seb.task.exceptions.HighIncomeException;
import com.seb.task.exceptions.IsNotStudentException;
import com.seb.task.exceptions.MiddleIncomeException;
import com.seb.task.exceptions.OverAgeException;
import com.seb.task.exceptions.UnderAgeException;
import com.seb.task.exceptions.WeakBundleException;
import com.seb.task.exceptions.ZeroIncomeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RuleCheckerServiceTest {

   @Autowired
   private RuleCheckerService ruleCheckerService;

   @Test
   public void isCurrentAccountAllowedTest(){
      CustomerAnswersDto dto = new CustomerAnswersDto();
      dto.setAge(18);
      dto.setIncome(2000);
      Assertions.assertTrue(ruleCheckerService.isCurrentAccountAllowed(dto));
      dto.setIncome(0);
      try {
         ruleCheckerService.isCurrentAccountAllowed(dto);
      } catch(RuntimeException ex) {
         Assertions.assertTrue(ex instanceof ZeroIncomeException);
      }

      dto.setAge(17);
      dto.setIncome(2000);
      try {
         ruleCheckerService.isCurrentAccountAllowed(dto);
      } catch(RuntimeException ex) {
         Assertions.assertTrue(ex instanceof UnderAgeException);
      }

   }

   @Test
   public  void isCurrentAccountPlusAllowedTest() {
      CustomerAnswersDto dto = new CustomerAnswersDto();
      dto.setAge(18);
      dto.setIncome(41000);
      Assertions.assertTrue(ruleCheckerService.isCurrentAccountPlusAllowed(dto));
      dto.setIncome(15000);
      try {
         ruleCheckerService.isCurrentAccountAllowed(dto);
      } catch(RuntimeException ex) {
         Assertions.assertTrue(ex instanceof HighIncomeException);
      }

   }

   @Test
   public void isJuniorSaverAccountAllowedTest() {
      CustomerAnswersDto dto = new CustomerAnswersDto();
      dto.setAge(18);
      dto.setIncome(41000);
      try {
         ruleCheckerService.isCurrentAccountAllowed(dto);
      } catch(RuntimeException ex) {
         Assertions.assertTrue(ex instanceof OverAgeException);
      }
      dto.setAge(17);
      Assertions.assertTrue(ruleCheckerService.isJuniorSaverAccountAllowed(dto));
   }

   @Test
   public void isStudentAccountAllowedTest() {
      CustomerAnswersDto dto = new CustomerAnswersDto();
      dto.setAge(18);
      dto.setIncome(41000);
      try {
         ruleCheckerService.isStudentAccountAllowed(dto);
      } catch (RuntimeException ex) {
         Assertions.assertTrue(ex instanceof IsNotStudentException );
      }
      dto.setStudent(true);
      Assertions.assertTrue(ruleCheckerService.isStudentAccountAllowed(dto));
   }

   @Test
   public void isDebitCardAllowedTest() {
      for(BundleType bundleType : BundleType.values()) {
         if(bundleType == BundleType.JUNIOR_SAVER) {
            try {
               ruleCheckerService.isDebitCardAllowed(bundleType);
            } catch(RuntimeException ex) {
               Assertions.assertTrue(ex instanceof WeakBundleException);
            }

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
      try {
         ruleCheckerService.isCreditCardAllowed(dto);
      } catch (RuntimeException ex) {
         Assertions.assertTrue(ex instanceof MiddleIncomeException);
      }

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
      try {
         ruleCheckerService.isGoldCreditCardAllowed(dto);
      }catch (RuntimeException ex) {
         Assertions.assertTrue(ex instanceof HighIncomeException);
      }
      dto.setIncome(1000);
   }

   @Test
   public void isThisTypeOfAccountAllowedTest() {
      CustomerAnswersDto dto = new CustomerAnswersDto();
      dto.setAge(18);
      dto.setIncome(41000);
      AccountType accountType = AccountType.JUNIOR_SAVER_ACCOUNT;
      try {
         ruleCheckerService.isThisTypeOfAccountAllowed(accountType,dto);
      } catch (RuntimeException ex) {
         Assertions.assertTrue(ex instanceof OverAgeException);
      }

      dto.setAge(17);
      Assertions.assertTrue(ruleCheckerService.isThisTypeOfAccountAllowed(accountType,dto));
      accountType = AccountType.CURRENT_ACCOUNT_PLUS;
      try {
         ruleCheckerService.isThisTypeOfAccountAllowed(accountType,dto);
      } catch (RuntimeException ex) {
         Assertions.assertTrue(ex instanceof UnderAgeException);
      }
      dto.setAge(28);
      Assertions.assertTrue(ruleCheckerService.isThisTypeOfAccountAllowed(accountType,dto));
      dto.setIncome(20000);
      try {
         ruleCheckerService.isThisTypeOfAccountAllowed(accountType,dto);
      } catch (RuntimeException ex) {
         Assertions.assertTrue(ex instanceof HighIncomeException);
      }
   }

}

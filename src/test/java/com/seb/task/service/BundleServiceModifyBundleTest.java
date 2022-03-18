package com.seb.task.service;

import com.seb.task.dto.BundleModificationDto;
import com.seb.task.dto.CustomerAnswersDto;
import com.seb.task.entity.bundle.Bundle;
import com.seb.task.entity.accounts.AccountType;
import com.seb.task.exceptions.HighIncomeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BundleServiceModifyBundleTest {

   @Autowired
   private BundleService bundleService;

   @Test
   public void testModifyBundleFromGold() {
      int age = 27;
      boolean student = false;
      int income = 50000;
      CustomerAnswersDto customerAnswersDto = new CustomerAnswersDto();
      customerAnswersDto.setAge(age);
      customerAnswersDto.setStudent(student);
      customerAnswersDto.setIncome(income);
      Bundle recommendedBundle = bundleService.recommendBundle(customerAnswersDto);
      BundleModificationDto dto = new BundleModificationDto();
      dto.setBundleType(recommendedBundle.getBundleType().name());
      dto.setCustomerAnswersDto(customerAnswersDto);
      dto.setNewAccountType("CURRENT_ACCOUNT");
      dto.getCardsToBeRemoved().add("DEBIT_CARD");
      dto.getCardsToBeRemoved().add("GOLD_CREDIT_CARD");
      Bundle modifiedBundle = bundleService.modifyBundle(dto);
      Assertions.assertNotNull(modifiedBundle);
      Assertions.assertEquals(AccountType.CURRENT_ACCOUNT, modifiedBundle.getAccount().getAccountType());
      Assertions.assertEquals(0,modifiedBundle.getCardList().size());
   }

   @Test
   public void testModifyBundleUserWithNoIncomeUpgradesToGold(){
      int age = 27;
      boolean student = true;
      int income = 0;
      CustomerAnswersDto customerAnswersDto = new CustomerAnswersDto();
      customerAnswersDto.setAge(age);
      customerAnswersDto.setStudent(student);
      customerAnswersDto.setIncome(income);
      Bundle recommendedBundle = bundleService.recommendBundle(customerAnswersDto);
      BundleModificationDto dto = new BundleModificationDto();
      dto.setBundleType(recommendedBundle.getBundleType().name());
      dto.setCustomerAnswersDto(customerAnswersDto);
      dto.setNewAccountType("CURRENT_ACCOUNT_PLUS");
      dto.getCardsToBeAdded().add("GOLD_CREDIT_CARD");
      try {
         bundleService.modifyBundle(dto);
      } catch (RuntimeException ex) {
         Assertions.assertTrue(ex instanceof  HighIncomeException);
      }

   }
}

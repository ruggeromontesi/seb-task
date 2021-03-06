package com.seb.task.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.seb.task.constants.HomeAssignmentConstants;
import com.seb.task.dto.BundleModificationDto;
import com.seb.task.dto.CustomerAnswersDto;
import com.seb.task.entity.bundle.Bundle;
import com.seb.task.entity.bundle.BundleType;
import com.seb.task.entity.accounts.Account;
import com.seb.task.entity.accounts.AccountType;
import com.seb.task.entity.cards.Card;
import com.seb.task.entity.cards.CardType;
import com.seb.task.exceptions.NoSuitableProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BundleService {

   @Autowired
   private RuleCheckerService ruleCheckerService;

   public Bundle recommendBundle(CustomerAnswersDto dto) {

      int age = dto.getAge();
      boolean student = dto.isStudent();
      double income = dto.getIncome();

      if (age < HomeAssignmentConstants.AGE_EIGHTEEN) {
         return new Bundle(BundleType.JUNIOR_SAVER);
      }

      if (student && income == 0) {
         return  new Bundle(BundleType.STUDENT);
      } else {
         if (income > HomeAssignmentConstants.ZERO_INCOME) {
            if (income > HomeAssignmentConstants.HIGH_INCOME) {
               return new Bundle(BundleType.GOLD);
            } else {
               if (income > HomeAssignmentConstants.MIDDLE_INCOME) {
                  return  new Bundle(BundleType.CLASSIC_PLUS);
               } else {
                  return  new Bundle(BundleType.CLASSIC);
               }
            }
         } else {
            throw new NoSuitableProductException("No suitable product");
         }
      }
   }

   public Bundle modifyBundle(BundleModificationDto dto) {

      BundleType bundleType = BundleType.valueOf(dto.getBundleType());

      ruleCheckerService.checkBundleConsistency(bundleType,dto.getCustomerAnswersDto());

      Bundle returnBundle = new Bundle(bundleType);

      AccountType accountType = null;
      Account newAccount = null;
      if (dto.getNewAccountType() != null) {
         accountType = AccountType.valueOf(dto.getNewAccountType());
         newAccount = new Account(accountType);
      }

      if (accountType != null) {
         if (ruleCheckerService.isThisTypeOfAccountAllowed(accountType,dto.getCustomerAnswersDto())) {
            returnBundle.setAccount(newAccount);
         }
      }

      List<CardType> listOfCardTypesToBeRemoved = dto.getCardsToBeRemoved()
            .stream().map(CardType::valueOf).collect(Collectors.toList());

      returnBundle.removeCards(listOfCardTypesToBeRemoved);

      List<CardType> listOfCardTypesToBeAdded = new ArrayList<>();

      for (String card : dto.getCardsToBeAdded()) {
         CardType cardType = CardType.valueOf(card);
         if (ruleCheckerService.isThisTypeOfCardAllowed(cardType,dto.getCustomerAnswersDto(),returnBundle.getBundleType())) {
            listOfCardTypesToBeAdded.add(cardType);
         }
      }

      List<CardType> cardTypeList = returnBundle.getCardList()
            .stream().map(card -> card.getCardType()).collect(Collectors.toList());
      for (CardType cardType : listOfCardTypesToBeAdded) {
         if (!cardTypeList.contains(cardType)) {
            returnBundle.getCardList().add(new Card(cardType));
         }
      }
      returnBundle.setBundleType(BundleType.CUSTOMIZED);
      return returnBundle;
   }
}

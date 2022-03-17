package com.seb.task.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.seb.task.dto.BundleModificationDto;
import com.seb.task.dto.CustomerAnswersDto;
import com.seb.task.entity.bundle.Bundle;
import com.seb.task.entity.bundle.BundleType;
import com.seb.task.entity.bundle.ClassicBundle;
import com.seb.task.entity.bundle.ClassicPlusBundle;
import com.seb.task.entity.bundle.GoldBundle;
import com.seb.task.entity.bundle.JuniorSaverBundle;
import com.seb.task.entity.bundle.StudentBundle;
import com.seb.task.entity.product.accounts.Account;
import com.seb.task.entity.product.accounts.AccountType;
import com.seb.task.entity.product.cards.Card;
import com.seb.task.entity.product.cards.CardType;
import com.seb.task.exceptions.InvalidCardException;
import com.seb.task.exceptions.NoSuitableProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BundleService {

   @Autowired
   private RuleCheckerService ruleCheckerService ;

   public Optional<Bundle> returnBundle(CustomerAnswersDto dto) {
      Bundle bundle = null;
      bundle = recommendBundle(dto.getAge(), dto.isStudent(), dto.getIncome()).get();
      return Optional.of(bundle);

   }

   public Optional<Bundle> recommendBundle(int age, boolean student, int income) {

      if (age < 18) {
         return Optional.of(new JuniorSaverBundle());
      }

      if (student && income == 0) {
         return  Optional.of(new StudentBundle());
      } else {
         if (income > 0) {
            if (income > 40000) {
               return  Optional.of(new GoldBundle());
            } else {
               if (income > 12000) {
                  return  Optional.of(new ClassicPlusBundle());
               } else {
                  return  Optional.of(new ClassicBundle());
               }
            }
         } else {
            throw new NoSuitableProductException("No suitable product");
         }
      }
   }

   public Optional<Bundle> modifyBundle(BundleModificationDto dto) {

      int age = dto.getCustomerAnswersDto().getAge();
      boolean student = dto.getCustomerAnswersDto().isStudent();
      int income = dto.getCustomerAnswersDto().getIncome();

      BundleType bundleType = BundleType.valueOf(dto.getBundleType());

      ruleCheckerService.checkBundleConsistency(bundleType,dto.getCustomerAnswersDto());




      Bundle returnBundle = Bundle.getBundleFromBundleType(bundleType);

      AccountType accountType = null;
      Account newAccount = null;
      if (dto.getAccountType() != null) {
         accountType = AccountType.valueOf(dto.getAccountType());
         newAccount = Account.getAccountFromAccountType(accountType);
      }



      if (accountType != null) {
         if (ruleCheckerService.isThisTypeOfAccountAllowed(accountType,dto.getCustomerAnswersDto())) {
            returnBundle.setAccount(newAccount);
         }

      }


      List<CardType> listOfCardTypesToBeRemoved = new ArrayList<>();

      for (String card : dto.getCardsToBeRemoved()) {
         CardType cardType;
         try {
            cardType = CardType.valueOf(card);
         } catch (IllegalArgumentException exception) {
            throw new InvalidCardException("Invalid Card Type", card);
         }
         listOfCardTypesToBeRemoved.add(cardType);
      }

      returnBundle.removeCards(listOfCardTypesToBeRemoved);

      List<CardType> listOfCardTypesToBeAdded = new ArrayList<>();

      for (String card : dto.getCardsToBeAdded()) {
         CardType cardType;
         try {
            cardType = CardType.valueOf(card);
         } catch (IllegalArgumentException exception) {
            throw new InvalidCardException("Invalid Card Type", card);
         }
         if (ruleCheckerService.isThisTypeOfCardAllowed(cardType,dto.getCustomerAnswersDto(),returnBundle.getBundleType())) {
            listOfCardTypesToBeAdded.add(cardType);
         }

      }

      List<CardType> cardTypeList = returnBundle.getCardList().stream().map(card -> card.getCardType()).collect(Collectors.toList());
      for(CardType cardType : listOfCardTypesToBeAdded) {

         if(!cardTypeList.contains(cardType)) {
            returnBundle.getCardList().add(Card.getCardFromCardType(cardType));
         }
      }

      return Optional.of(returnBundle);
   }









}

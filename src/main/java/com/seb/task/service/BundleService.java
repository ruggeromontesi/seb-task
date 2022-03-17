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
   private RuleCheckerService ruleCheckerService;

   public Bundle returnBundle(CustomerAnswersDto dto) {
      Bundle bundle;
      bundle = recommendBundle(dto.getAge(), dto.isStudent(), dto.getIncome());
      return bundle;
   }

   public Bundle recommendBundle(int age, boolean student, int income) {

      if (age < 18) {
         return new JuniorSaverBundle();
      }

      if (student && income == 0) {
         return  new StudentBundle();
      } else {
         if (income > 0) {
            if (income > 40000) {
               return  new GoldBundle();
            } else {
               if (income > 12000) {
                  return  new ClassicPlusBundle();
               } else {
                  return  new ClassicBundle();
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

      List<CardType> cardTypeList = returnBundle.getCardList().stream().map(card -> card.getCardType()).collect(Collectors.toList());
      for (CardType cardType : listOfCardTypesToBeAdded) {
         if (!cardTypeList.contains(cardType)) {
            returnBundle.getCardList().add(Card.getCardFromCardType(cardType));
         }
      }
      return returnBundle;
   }
}

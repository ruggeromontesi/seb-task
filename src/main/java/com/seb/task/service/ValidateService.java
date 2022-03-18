package com.seb.task.service;

import java.util.List;

import com.seb.task.constants.HomeAssignmentConstants;
import com.seb.task.dto.BundleModificationDto;
import com.seb.task.dto.CustomerAnswersDto;
import com.seb.task.entity.bundle.BundleType;
import com.seb.task.entity.product.accounts.AccountType;
import com.seb.task.entity.product.cards.CardType;
import com.seb.task.exceptions.InvalidAccountException;
import com.seb.task.exceptions.InvalidAgeException;
import com.seb.task.exceptions.InvalidBundleException;
import com.seb.task.exceptions.InvalidCardException;
import com.seb.task.exceptions.InvalidIncomeException;
import com.seb.task.exceptions.MissingAgeInCustomerDtoException;
import com.seb.task.exceptions.MissingIncomeInCustomerDtoException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ValidateService {
   /** This method checks that the dto with client answers has reasonable values, i.e.: age included within minimum and maximum
    * value and that income is a non negative integer
    * @param customerAnswersDto the entity containing client's answers
    * @return true if dto matches the validity criteria
    */
   public boolean customerAnswerDtoValidator(@NotNull CustomerAnswersDto customerAnswersDto) {

      if (customerAnswersDto.getAge() == null) {
         throw new MissingAgeInCustomerDtoException("Missing age in customer's answers!");
      }

      if (customerAnswersDto.getIncome() == null) {
         throw new MissingIncomeInCustomerDtoException("Missing income in customer's answers!");
      }

      if (customerAnswersDto.getAge() < HomeAssignmentConstants.MINIMUM_AGE + 1
            || customerAnswersDto.getAge() > HomeAssignmentConstants.MAXIMUM_AGE) {
         throw  new InvalidAgeException("Invalid age!",customerAnswersDto.getAge());
      }
      if (customerAnswersDto.getIncome() < HomeAssignmentConstants.ZERO_INCOME ) {
         throw new InvalidIncomeException("Invalid income!",customerAnswersDto.getIncome());
      }
      return true;
   }

   /**This method checks that the BundleModificationDto contains valid values for its fields.
    * @param bundleModificationDto : is the entity passed to the method including fields representing the proposed bundle and
    *                              request of customization from client.
    *
    * @return true if the test is successful otherwise relevant exceptions are thrown.
    */
   public boolean validateModificationBundleDto(@NotNull BundleModificationDto bundleModificationDto) {
      if (!customerAnswerDtoValidator(bundleModificationDto.getCustomerAnswersDto())) {
         return false;
      }

      if (bundleModificationDto.getBundleType() == null) {
         throw new InvalidBundleException("Invalid bundle Type",bundleModificationDto.getBundleType());
      }

      try {
         BundleType.valueOf(bundleModificationDto.getBundleType());
      } catch (IllegalArgumentException exception) {
         throw new InvalidBundleException("Invalid bundle Type",bundleModificationDto.getBundleType());
      }

      if (bundleModificationDto.getNewAccountType() != null) {
         try {
            AccountType.valueOf(bundleModificationDto.getNewAccountType());
         } catch (IllegalArgumentException exception) {
            throw new InvalidAccountException("Invalid account Type",bundleModificationDto.getNewAccountType());
         }
      }

      List<String> cardList = bundleModificationDto.getCardsToBeRemoved();

      for (String card : cardList) {

         try {
            CardType.valueOf(card);
         } catch (IllegalArgumentException exception) {
            throw new InvalidCardException("Invalid Card Type", card);
         }
      }

      cardList = bundleModificationDto.getCardsToBeAdded();
      for (String card : cardList) {

         try {
            CardType.valueOf(card);
         } catch (IllegalArgumentException exception) {
            throw new InvalidCardException("Invalid Card Type", card);
         }
      }
      return true;
   }
}

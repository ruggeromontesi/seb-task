package com.seb.task.service;

import com.seb.task.constants.HomeAssignmentConstants;
import com.seb.task.dto.CustomerAnswersDto;
import com.seb.task.entity.bundle.Bundle;
import com.seb.task.entity.bundle.BundleType;
import com.seb.task.entity.product.accounts.AccountType;
import com.seb.task.entity.product.cards.CardType;
import com.seb.task.exceptions.HighIncomeException;
import com.seb.task.exceptions.IsNotStudentException;
import com.seb.task.exceptions.MiddleIncomeException;
import com.seb.task.exceptions.OverAgeException;
import com.seb.task.exceptions.UnderAgeException;
import com.seb.task.exceptions.WeakBundleException;
import com.seb.task.exceptions.ZeroIncomeException;
import org.springframework.stereotype.Service;

@Service
public class RuleCheckerService {

   public boolean isCurrentAccountAllowed(CustomerAnswersDto dto) {
      if (dto.getIncome() < HomeAssignmentConstants.ZERO_INCOME + 1) {
         throw new ZeroIncomeException("Zero income", dto.getIncome());
      }

      if (dto.getAge() < HomeAssignmentConstants.AGE_EIGHTEEN) {
         throw new UnderAgeException("UnderAgeException", dto.getAge());
      }

      return true;
   }

   public boolean  isCurrentAccountPlusAllowed(CustomerAnswersDto dto) {
      if(dto.getIncome() < HomeAssignmentConstants.HIGH_INCOME + 1) {
         throw  new HighIncomeException("HighIncomeException", dto.getIncome());
      }

      if (dto.getAge() < HomeAssignmentConstants.AGE_EIGHTEEN) {
         throw new UnderAgeException("UnderAgeException", dto.getAge());
      }
      return true;
   }

   public boolean isJuniorSaverAccountAllowed(CustomerAnswersDto dto) {
      if (dto.getAge() > HomeAssignmentConstants.AGE_SEVENTEEN) {
         throw new OverAgeException("Over Age Exception", dto.getAge());
      }
      return true;
   }

   public boolean isStudentAccountAllowed(CustomerAnswersDto dto) {
      if (!dto.isStudent()) {
         throw new IsNotStudentException("Is not a student exception");
      }
      if (dto.getAge() < HomeAssignmentConstants.AGE_EIGHTEEN) {
         throw new UnderAgeException("UnderAgeException", dto.getAge());
      }
      return true;
   }



   public boolean isThisTypeOfAccountAllowed(AccountType accountType,CustomerAnswersDto dto) {

      if (accountType == AccountType.CURRENT_ACCOUNT) {
         return  isCurrentAccountAllowed(dto);
      }

      if (accountType == AccountType.CURRENT_ACCOUNT_PLUS) {
         return  isCurrentAccountPlusAllowed(dto);
      }

      if (accountType == AccountType.JUNIOR_SAVER_ACCOUNT) {
         return  isJuniorSaverAccountAllowed(dto);
      }

      if (accountType == AccountType.STUDENT_ACCOUNT) {
         return  isStudentAccountAllowed(dto);
      }

      return false;
   }

   public void checkBundleConsistency(BundleType bundleType, CustomerAnswersDto dto) {

      Bundle bundle = new Bundle(bundleType);

      AccountType accountType = bundle.getAccount().getAccountType();

      isThisTypeOfAccountAllowed(accountType,dto);

   }

   public boolean isDebitCardAllowed(BundleType bundleType) {
      if (bundleType == BundleType.JUNIOR_SAVER) {
         throw new WeakBundleException("Weak bundle exception");
      }
      return true;
   }

   public boolean isCreditCardAllowed(CustomerAnswersDto dto) {

      if(dto.getIncome() < HomeAssignmentConstants.MIDDLE_INCOME + 1) {
         throw  new MiddleIncomeException("Middle Income Exception", dto.getIncome());
      }

      if (dto.getAge() < HomeAssignmentConstants.AGE_EIGHTEEN) {
         throw new UnderAgeException("UnderAgeException", dto.getAge());
      }
      return true;

   }

   public boolean isGoldCreditCardAllowed(CustomerAnswersDto dto) {
      return isCurrentAccountPlusAllowed(dto);
   }

   public boolean isThisTypeOfCardAllowed(CardType cardType, CustomerAnswersDto dto, BundleType bundleType) {

      if(cardType == CardType.DEBIT_CARD) {
         return isDebitCardAllowed(bundleType);
      }

      if(cardType == CardType.CREDIT_CARD) {
         return isCreditCardAllowed(dto);
      }

      if (cardType == CardType.GOLD_CREDIT_CARD) {
         return isGoldCreditCardAllowed(dto);
      }

      return false;
   }
}

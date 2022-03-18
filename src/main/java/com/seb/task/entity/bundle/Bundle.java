package com.seb.task.entity.bundle;

import java.util.ArrayList;
import java.util.List;

import com.seb.task.entity.accounts.Account;
import com.seb.task.entity.accounts.CurrentAccount;
import com.seb.task.entity.accounts.CurrentAccountPlus;
import com.seb.task.entity.accounts.JuniorSaverAccount;
import com.seb.task.entity.accounts.StudentAccount;
import com.seb.task.entity.cards.Card;
import com.seb.task.entity.cards.CardType;
import com.seb.task.entity.cards.CreditCard;
import com.seb.task.entity.cards.DebitCard;
import com.seb.task.entity.cards.GoldCreditCard;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Bundle {

   @JsonIgnore
   protected int value;

   protected BundleType bundleType;

   protected Account account;

   protected List<Card> cardList = new ArrayList<>();

   public Bundle() {
   }

   public Bundle(BundleType bundleType) {

      if (bundleType == BundleType.CLASSIC) {
         this.bundleType = bundleType;
         this.account = new CurrentAccount();
         this.cardList.add(new DebitCard());
         this.value = 1;
      }

      if (bundleType == BundleType.CLASSIC_PLUS) {
         this.bundleType = bundleType;
         this.account = new CurrentAccount();
         this.cardList.add(new DebitCard());
         this.cardList.add(new CreditCard());
         this.value = 2;
      }

      if (bundleType == BundleType.GOLD) {
         this.bundleType = bundleType;
         this.account = new CurrentAccountPlus();
         this.cardList.add(new DebitCard());
         this.cardList.add(new GoldCreditCard());
         this.value = 3;
      }

      if (bundleType == BundleType.JUNIOR_SAVER) {
         this.bundleType = bundleType;
         this.account = new JuniorSaverAccount();
         this.cardList = new ArrayList<>();
         this.value = 0;
      }

      if (bundleType == BundleType.STUDENT) {
         this.bundleType = bundleType;
         this.account = new StudentAccount();
         this.cardList.add(new DebitCard());
         this.cardList.add(new CreditCard());
         this.value = 0;
      }

      if (bundleType == BundleType.CUSTOMIZED) {
         this.bundleType = bundleType;
      }




   }

   public int getValue() {
      return value;
   }

   public void setValue(int value) {
      this.value = value;
   }

   public BundleType getBundleType() {
      return bundleType;
   }

   public void setBundleType(BundleType bundleType) {
      this.bundleType = bundleType;
   }

   public Account getAccount() {
      return account;
   }

   public void setAccount(Account account) {
      this.account = account;
   }

   public List<Card> getCardList() {
      return cardList;
   }

   public void removeCards(List<CardType> cardTypeList) {
      for (CardType cardType : cardTypeList) {
         cardList.removeIf(card -> card.getCardType() == cardType);
      }
   }

}

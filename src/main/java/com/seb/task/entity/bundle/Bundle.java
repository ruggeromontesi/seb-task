package com.seb.task.entity.bundle;

import java.util.ArrayList;
import java.util.List;

import com.seb.task.entity.product.accounts.Account;
import com.seb.task.entity.product.accounts.CurrentAccount;
import com.seb.task.entity.product.accounts.CurrentAccountPlus;
import com.seb.task.entity.product.accounts.JuniorSaverAccount;
import com.seb.task.entity.product.accounts.StudentAccount;
import com.seb.task.entity.product.cards.Card;
import com.seb.task.entity.product.cards.CardType;
import com.seb.task.entity.product.cards.CreditCard;
import com.seb.task.entity.product.cards.DebitCard;
import com.seb.task.entity.product.cards.GoldCreditCard;

public class Bundle {

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
      for(CardType cardType : cardTypeList) {
         cardList.removeIf(card -> card.getCardType() == cardType);
      }
   }

   public static Bundle getBundleFromBundleType(BundleType bundleType) {
      Bundle returnBundle = null;

      if (bundleType == BundleType.JUNIOR_SAVER) {
         returnBundle = new JuniorSaverBundle();
      }

      if (bundleType == BundleType.STUDENT) {
         returnBundle = new StudentBundle();
      }

      if (bundleType == BundleType.CLASSIC) {
         returnBundle = new ClassicBundle();
      }

      if (bundleType == BundleType.CLASSIC_PLUS) {
         returnBundle = new ClassicPlusBundle();
      }

      if (bundleType == BundleType.GOLD) {
         returnBundle = new GoldBundle();
      }

      return returnBundle;
   }

}

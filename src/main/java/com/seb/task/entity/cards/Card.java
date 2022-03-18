package com.seb.task.entity.cards;

public class Card {

   protected CardType cardType;

   public CardType getCardType() {
      return cardType;
   }

   public static Card getCardFromCardType(CardType cardType) {
      if (cardType == CardType.DEBIT_CARD) {
         return new DebitCard();
      }

      if (cardType == CardType.CREDIT_CARD) {
         return new CreditCard();
      }

      if (cardType == CardType.GOLD_CREDIT_CARD) {
         return new GoldCreditCard();
      }

      return null;


   }
}

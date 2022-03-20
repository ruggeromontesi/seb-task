package com.seb.task.entity.cards;

public class Card {

   protected CardType cardType;

   public Card(CardType cardType) {
      this.cardType = cardType;
   }

   public CardType getCardType() {
      return cardType;
   }


}

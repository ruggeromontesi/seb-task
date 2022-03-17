
package com.seb.task.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.seb.task.entity.bundle.Bundle;
import com.seb.task.entity.bundle.GoldBundle;
import com.seb.task.entity.product.cards.CardType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BundleTest {

   @Test
   public void testRemoveCards() {
      Bundle bundle = new GoldBundle();
      CardType debitCardType = CardType.DEBIT_CARD;
      CardType goldCreditCardType = CardType.GOLD_CREDIT_CARD;
      CardType creditCardType = CardType.CREDIT_CARD;
      List<CardType> cardsToBeRemoved = new ArrayList<>();
      cardsToBeRemoved.add(debitCardType);
      cardsToBeRemoved.add(goldCreditCardType);
      cardsToBeRemoved.add(creditCardType);

      bundle.removeCards(cardsToBeRemoved);
      Assertions.assertTrue(bundle.getCardList().isEmpty());
   }

   @Test
   public void testGetCardTypeList() {
      Bundle bundle = new GoldBundle();
      List<CardType> cardTypeList = bundle.getCardList().stream().map(card -> card.getCardType()).collect(Collectors.toList());
     Assertions.assertEquals(2,cardTypeList.size());

   }
}

package com.seb.task.entity.bundle;

import java.util.ArrayList;
import java.util.List;

import com.seb.task.entity.product.accounts.Account;
import com.seb.task.entity.product.cards.Card;
import com.seb.task.entity.product.cards.CardType;

public class Bundle {

    protected int value;

    protected BundleType bundleType;

    protected Account account;

    protected List<Card> cardList = new ArrayList<>();

    public Bundle() {
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

    public void addCards(List<CardType> cardTypeList) {
        for(CardType cardType : cardTypeList) {
            for(Card card : cardList) {
                ;
            }
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

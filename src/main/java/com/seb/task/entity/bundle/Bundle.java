package com.seb.task.entity.bundle;

import com.seb.task.entity.Customer;
import com.seb.task.entity.product.accounts.Account;
import com.seb.task.entity.product.cards.Card;

import java.util.ArrayList;
import java.util.List;

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

    public BundleType getBundleType() {
        return bundleType;
    }

    public Account getAccount() {
        return account;
    }

    public List<Card> getCardList() {
        return cardList;
    }
}

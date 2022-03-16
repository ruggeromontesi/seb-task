package com.seb.task.entity.bundle;

import com.seb.task.entity.product.accounts.CurrentAccountPlus;
import com.seb.task.entity.product.cards.DebitCard;
import com.seb.task.entity.product.cards.GoldCreditCard;

public class GoldBundle extends Bundle{
    public GoldBundle() {
        this.bundleType = BundleType.GOLD;
        this.account = new CurrentAccountPlus();
        this.cardList.add(new DebitCard());
        this.cardList.add(new GoldCreditCard());
        this.value = 3;
    }
}

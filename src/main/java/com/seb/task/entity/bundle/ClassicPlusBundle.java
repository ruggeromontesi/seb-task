package com.seb.task.entity.bundle;

import com.seb.task.entity.product.accounts.CurrentAccount;
import com.seb.task.entity.product.cards.CreditCard;
import com.seb.task.entity.product.cards.DebitCard;

public class ClassicPlusBundle extends Bundle{

    public ClassicPlusBundle() {
        this.bundleType = BundleType.CLASSIC_PLUS;
        this.account = new CurrentAccount();
        this.cardList.add(new DebitCard());
        this.cardList.add(new CreditCard());
        this.value = 2;
    }
}

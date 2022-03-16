package com.seb.task.entity.bundle;

import com.seb.task.entity.product.accounts.StudentAccount;
import com.seb.task.entity.product.cards.CreditCard;
import com.seb.task.entity.product.cards.DebitCard;

public class StudentBundle extends Bundle{

    public StudentBundle() {
        this.bundleType = BundleType.STUDENT;
        this.account = new StudentAccount();
        this.cardList.add(new DebitCard());
        this.cardList.add(new CreditCard());
        this.value = 0;
    }
}

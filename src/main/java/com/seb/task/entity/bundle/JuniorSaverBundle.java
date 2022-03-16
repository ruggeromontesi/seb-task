package com.seb.task.entity.bundle;

import com.seb.task.entity.product.accounts.JuniorSaverAccount;

import java.util.ArrayList;

public class JuniorSaverBundle extends  Bundle{

    public JuniorSaverBundle() {
        this.bundleType =  BundleType.JUNIOR_SAVER;
        this.account = new JuniorSaverAccount();
        this.cardList = new ArrayList<>();
        this.value = 0;

    }
}

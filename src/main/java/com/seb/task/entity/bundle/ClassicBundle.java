package com.seb.task.entity.bundle;

import com.seb.task.entity.product.accounts.CurrentAccount;
import com.seb.task.entity.product.cards.DebitCard;

public class ClassicBundle extends  Bundle{

   public ClassicBundle() {
      this.bundleType = BundleType.CLASSIC;
      this.account = new CurrentAccount();
      this.cardList.add(new DebitCard());
      this.value = 1;
   }
}

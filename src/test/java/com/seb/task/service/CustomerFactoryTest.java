package com.seb.task.service;

import com.seb.task.entity.bundle.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerFactoryTest {

    private BundleService factory = new BundleService();

    @Test
    public void testRecommendBundleJuniorSaver() {
        int age = 17;
        boolean student = false;
        int income = 0;
        Bundle resultBundle = factory.recommendBundle(age,student,income).get();
        Assertions.assertTrue(resultBundle instanceof JuniorSaverBundle);
   }

    @Test
    public void testRecommendBundleNone() {
        int age = 18;
        boolean student = false;
        int income = 0;
        Bundle resultBundle = factory.recommendBundle(age,student,income).orElse(null);
        Assertions.assertNull(resultBundle);
    }

    @Test
    public void testRecommendBundleStudent() {
        int age = 18;
        boolean student = true;
        int income = 0;
        Bundle resultBundle = factory.recommendBundle(age,student,income).get();
        Assertions.assertTrue(resultBundle instanceof StudentBundle);
    }

    @Test
    public void testRecommendBundleClassic() {
        int age = 18;
        boolean student = true;
        int income = 10;
        Bundle resultBundle = factory.recommendBundle(age,student,income).get();
        Assertions.assertTrue(resultBundle instanceof ClassicBundle);
    }


    @Test
    public void testRecommendBundleClassicPlus() {
        int age = 18;
        boolean student = false;
        int income = 15000;
        Bundle resultBundle = factory.recommendBundle(age,student,income).get();
        Assertions.assertTrue(resultBundle instanceof ClassicPlusBundle);
    }

    @Test
    public void testRecommendBundleGold() {
        int age = 18;
        boolean student = false;
        int income = 45000;
        Bundle resultBundle = factory.recommendBundle(age,student,income).get();
        Assertions.assertTrue(resultBundle instanceof GoldBundle);
    }








}

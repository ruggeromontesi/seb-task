package com.seb.task.service;

import com.seb.task.entity.bundle.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BundleServiceRecommendBundleTest {

    @Autowired
    private BundleService bundleService;

    @Test
    public void testRecommendBundleJuniorSaver() {
        int age = 17;
        boolean student = false;
        int income = 0;
        Bundle resultBundle = bundleService.recommendBundle(age,student,income);
        Assertions.assertTrue(resultBundle instanceof JuniorSaverBundle);
   }

    @Test
    public void testRecommendBundleNone() {
        int age = 18;
        boolean student = false;
        int income = 0;
        Bundle resultBundle = bundleService.recommendBundle(age,student,income);
        Assertions.assertNull(resultBundle);
    }

    @Test
    public void testRecommendBundleStudent() {
        int age = 18;
        boolean student = true;
        int income = 0;
        Bundle resultBundle = bundleService.recommendBundle(age,student,income);
        Assertions.assertTrue(resultBundle instanceof StudentBundle);
    }

    @Test
    public void testRecommendBundleClassic() {
        int age = 18;
        boolean student = true;
        int income = 10;
        Bundle resultBundle = bundleService.recommendBundle(age,student,income);
        Assertions.assertTrue(resultBundle instanceof ClassicBundle);
    }


    @Test
    public void testRecommendBundleClassicPlus() {
        int age = 18;
        boolean student = false;
        int income = 15000;
        Bundle resultBundle = bundleService.recommendBundle(age,student,income);
        Assertions.assertTrue(resultBundle instanceof ClassicPlusBundle);
    }

    @Test
    public void testRecommendBundleGold() {
        int age = 18;
        boolean student = false;
        int income = 45000;
        Bundle resultBundle = bundleService.recommendBundle(age,student,income);
        Assertions.assertTrue(resultBundle instanceof GoldBundle);
    }

}
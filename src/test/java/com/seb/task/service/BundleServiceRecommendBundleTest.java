package com.seb.task.service;

import com.seb.task.dto.CustomerAnswersDto;
import com.seb.task.entity.bundle.*;
import com.seb.task.exceptions.NoSuitableProductException;
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
        CustomerAnswersDto  customerAnswersDto = new CustomerAnswersDto();
        customerAnswersDto.setAge(age);
        customerAnswersDto.setStudent(student);
        customerAnswersDto.setIncome(income);
        Bundle resultBundle = bundleService.recommendBundle(customerAnswersDto);
        Assertions.assertEquals(BundleType.JUNIOR_SAVER, resultBundle.getBundleType());

   }

    @Test
    public void testRecommendBundleNone() {
        int age = 18;
        boolean student = false;
        int income = 0;
        CustomerAnswersDto  customerAnswersDto = new CustomerAnswersDto();
        customerAnswersDto.setAge(age);
        customerAnswersDto.setStudent(student);
        customerAnswersDto.setIncome(income);
        try {
           bundleService.recommendBundle(customerAnswersDto);
        } catch (RuntimeException ex ){
           Assertions.assertTrue(ex instanceof NoSuitableProductException);
        }

    }

    @Test
    public void testRecommendBundleStudent() {
        int age = 18;
        boolean student = true;
        int income = 0;
        CustomerAnswersDto  customerAnswersDto = new CustomerAnswersDto();
        customerAnswersDto.setAge(age);
        customerAnswersDto.setStudent(student);
        customerAnswersDto.setIncome(income);
        Bundle resultBundle = bundleService.recommendBundle(customerAnswersDto);
        Assertions.assertEquals(BundleType.STUDENT, resultBundle.getBundleType());

    }

    @Test
    public void testRecommendBundleClassic() {
        int age = 18;
        boolean student = true;
        int income = 10;
        CustomerAnswersDto  customerAnswersDto = new CustomerAnswersDto();
        customerAnswersDto.setAge(age);
        customerAnswersDto.setStudent(student);
        customerAnswersDto.setIncome(income);
        Bundle resultBundle = bundleService.recommendBundle(customerAnswersDto);
        Assertions.assertEquals(BundleType.CLASSIC, resultBundle.getBundleType());

    }


    @Test
    public void testRecommendBundleClassicPlus() {
        int age = 18;
        boolean student = false;
        int income = 15000;
        CustomerAnswersDto  customerAnswersDto = new CustomerAnswersDto();
        customerAnswersDto.setAge(age);
        customerAnswersDto.setStudent(student);
        customerAnswersDto.setIncome(income);
        Bundle resultBundle = bundleService.recommendBundle(customerAnswersDto);
        Assertions.assertEquals(BundleType.CLASSIC_PLUS, resultBundle.getBundleType());
    }

    @Test
    public void testRecommendBundleGold() {
        int age = 18;
        boolean student = false;
        int income = 45000;
        CustomerAnswersDto  customerAnswersDto = new CustomerAnswersDto();
        customerAnswersDto.setAge(age);
        customerAnswersDto.setStudent(student);
        customerAnswersDto.setIncome(income);
        Bundle resultBundle = bundleService.recommendBundle(customerAnswersDto);
        Assertions.assertEquals(BundleType.GOLD, resultBundle.getBundleType());

    }
}

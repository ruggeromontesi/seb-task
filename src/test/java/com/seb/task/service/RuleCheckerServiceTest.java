package com.seb.task.service;

import com.seb.task.dto.CustomerAnswersDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RuleCheckerServiceTest {

    private RuleCheckerService ruleCheckerService = new RuleCheckerService();

    @Test
    public void isCurrentAccountAllowedTest(){
        CustomerAnswersDto dto = new CustomerAnswersDto();
        dto.setAge(18);
        dto.setIncome(2000);
        Assertions.assertTrue(ruleCheckerService.isCurrentAccountAllowed(dto));
        dto.setIncome(0);
        Assertions.assertFalse(ruleCheckerService.isCurrentAccountAllowed(dto));
        dto.setAge(17);
        dto.setIncome(2000);
        Assertions.assertFalse(ruleCheckerService.isCurrentAccountAllowed(dto));
    }

    
}

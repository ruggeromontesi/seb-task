package com.seb.task.service;

import com.seb.task.dto.CustomerAnswersDto;
import org.springframework.stereotype.Service;

@Service
public class RuleCheckerService {

    public boolean isCurrentAccountAllowed(CustomerAnswersDto dto) {
      return (dto.getIncome() > 0) && (dto.getAge() > 17);
    }



}

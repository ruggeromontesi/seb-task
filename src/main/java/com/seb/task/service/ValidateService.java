package com.seb.task.service;

import com.seb.task.dto.BundleModificationDto;
import com.seb.task.dto.CustomerAnswersDto;
import com.seb.task.entity.bundle.BundleType;
import com.seb.task.entity.product.accounts.AccountType;
import com.seb.task.entity.product.cards.CardType;
import com.seb.task.exceptions.InvalidAgeException;
import com.seb.task.exceptions.InvalidBundleException;
import com.seb.task.exceptions.InvalidCardException;
import com.seb.task.exceptions.InvalidIncomeException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidateService {

    public boolean customerAnswerDtoValidator(CustomerAnswersDto dto) {
        if(dto.getAge() <1 || dto.getAge() > 99) {
            throw  new InvalidAgeException("Invalid age!",dto.getAge());
        } else {

        }

        if (dto.getIncome() < 0) {
            throw new InvalidIncomeException("Invalid income!",dto.getIncome());
        }

        return true;
    }

    public boolean validateModificationBundleDto(BundleModificationDto dto) {
        if(!customerAnswerDtoValidator(dto.getCustomerAnswersDto())) {
            return false;
        }

        if(dto.getBundleType() == null) {
            throw new InvalidBundleException("Invalid bundle Type",dto.getBundleType());
        }

        BundleType bundleType;

        try {
            bundleType = BundleType.valueOf(dto.getBundleType());
        } catch (IllegalArgumentException exception) {
            throw new InvalidBundleException("Invalid bundle Type",dto.getBundleType());
        }

        AccountType accountType;
        try {
            accountType = AccountType.valueOf(dto.getAccountType());
        } catch (IllegalArgumentException exception) {
            throw new InvalidBundleException("Invalid account Type",dto.getAccountType());
        }

        List<String> cardList = dto.getCardList();
        List<CardType> cardTypeList = new ArrayList<>();
        for(String card : cardList) {
            CardType cardType;
            try {
                cardType = CardType.valueOf(card);
            } catch (IllegalArgumentException exception) {
                throw new InvalidCardException("Invalid Card Type", card);
            }


        }




        return false;
    }




}

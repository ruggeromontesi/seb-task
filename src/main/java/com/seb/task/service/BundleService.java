package com.seb.task.service;

import com.seb.task.dto.BundleModificationDto;
import com.seb.task.dto.CustomerAnswersDto;
import com.seb.task.entity.Customer;
import com.seb.task.entity.bundle.*;
import com.seb.task.entity.product.accounts.AccountType;
import com.seb.task.entity.product.cards.CardType;
import com.seb.task.exceptions.InvalidCardException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BundleService {

    public Customer create(int age, boolean student, int income){

        return null;
    }

    public Optional<Bundle> returnBundle(CustomerAnswersDto dto) {
        Bundle bundle = null;

        bundle = recommendBundle(dto.getAge(), dto.isStudent(), dto.getIncome()).get();
        return Optional.of(bundle);

    }


    public Optional<Bundle> recommendBundle(int age, boolean student, int income) {

        if ( age < 18  ) {
            return Optional.of(new JuniorSaverBundle());
        }
        if (age > 17) {
            if (student && income == 0) {
                 return  Optional.of(new StudentBundle());
            } else {
                if (income > 0) {
                    if (income > 40000) {
                        return  Optional.of(new GoldBundle());
                    } else {
                        if (income > 12000) {
                            return  Optional.of(new ClassicPlusBundle());
                        } else {
                            return  Optional.of(new ClassicBundle());
                        }
                    }
                } else {
                    return Optional.empty();
                }
            }
        }
        return  Optional.empty();
    }

    public Optional<Bundle> modifyBundle(BundleModificationDto dto) {

        int age = dto.getCustomerAnswersDto().getAge();

        boolean student = dto.getCustomerAnswersDto().isStudent();

        int income = dto.getCustomerAnswersDto().getIncome();

        BundleType bundleType = BundleType.valueOf(dto.getBundleType());
        AccountType accountType = AccountType.valueOf(dto.getAccountType());
        List<CardType> cardTypeList = new ArrayList<>();
        for(String card : dto.getCardList()) {
            CardType cardType;
            try {
                cardType = CardType.valueOf(card);
            } catch (IllegalArgumentException exception) {
                throw new InvalidCardException("Invalid Card Type", card);
            }
            cardTypeList.add(cardType);


        }



        return Optional.empty();
    }



}

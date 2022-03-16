package com.seb.task.service;

import com.seb.task.dto.BundleModificationDto;
import com.seb.task.dto.CustomerAnswersDto;
import com.seb.task.entity.Customer;
import com.seb.task.entity.bundle.*;
import org.springframework.stereotype.Service;

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
        return Optional.empty();
    }



}

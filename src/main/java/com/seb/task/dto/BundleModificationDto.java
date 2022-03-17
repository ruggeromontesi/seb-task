package com.seb.task.dto;

import java.util.ArrayList;
import java.util.List;


public class BundleModificationDto {

    private String bundleType;

    private CustomerAnswersDto customerAnswersDto;

    protected String accountType;

    protected List<String> cardsToBeRemoved = new ArrayList<>();

    protected List<String> cardsToBeAdded = new ArrayList<>();

    public BundleModificationDto() {
    }

    public String getBundleType() {
        return bundleType;
    }

    public void setBundleType(String bundleType) {
        this.bundleType = bundleType;
    }

    public CustomerAnswersDto getCustomerAnswersDto() {
        return customerAnswersDto;
    }

    public void setCustomerAnswersDto(CustomerAnswersDto customerAnswersDto) {
        this.customerAnswersDto = customerAnswersDto;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public List<String> getCardsToBeRemoved() {
        return cardsToBeRemoved;
    }

    public List<String> getCardsToBeAdded() {
        return cardsToBeAdded;
    }
}

package com.seb.task.dto;

import com.seb.task.entity.bundle.Bundle;


public class BundleModificationDto {

    private String bundleType;

    private CustomerAnswersDto customerAnswersDto;

    private Bundle modifiedBundle;

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

    public Bundle getModifiedBundle() {
        return modifiedBundle;
    }

    public void setModifiedBundle(Bundle modifiedBundle) {
        this.modifiedBundle = modifiedBundle;
    }
}

package com.seb.task.controller;

import com.seb.task.dto.BundleModificationDto;
import com.seb.task.dto.CustomerAnswersDto;
import com.seb.task.entity.bundle.Bundle;
import com.seb.task.entity.bundle.GoldBundle;
import com.seb.task.service.BundleService;
import com.seb.task.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class BundleController {

    @Autowired
    private BundleService bundleService;

    @Autowired
    private ValidateService validateService;

    @PostMapping(value ="/request")
    public ResponseEntity<Optional<Bundle>> returnBundle(@RequestBody CustomerAnswersDto dto) {
        if(validateService.customerAnswerDtoValidator(dto)) {
            Optional<Bundle> body = bundleService.returnBundle(dto);
            return ResponseEntity.ok(body);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

                    /*ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Error Message");*/
        }

    }


    @GetMapping("/hello")
    public String hello() {
        return String.format("Hello seb task !");
    }

    @GetMapping("/bundle")
    public Bundle getAccount() {
        int age = 18;
        boolean student = true;
        int income = 0;
        Bundle responseBundle = bundleService.recommendBundle(age,student,income).get();
        return  responseBundle;
    }

    @PostMapping(value ="/modify")
    public Bundle modifyBundle(@RequestBody BundleModificationDto dto) {
        validateService.validateModificationBundleDto(dto);

        return new GoldBundle();

    }



}

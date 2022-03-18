package com.seb.task.controller;

import com.seb.task.dto.BundleModificationDto;
import com.seb.task.dto.CustomerAnswersDto;
import com.seb.task.entity.bundle.Bundle;
import com.seb.task.service.BundleService;
import com.seb.task.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BundleController {

   @Autowired
   private BundleService bundleService;

   @Autowired
   private ValidateService validateService;

   /**Endpoint to get a recommended bundle, given client's answers.
    *@param customerAnswersDto contains client answers
    *@return ResponseEntity with recommended bundle or BAD_REQUEST status in case wrong parameters are supplied.
    */
   @PostMapping(value = "/request")
   public ResponseEntity<Bundle> returnBundle(@RequestBody CustomerAnswersDto customerAnswersDto) {
      if (validateService.customerAnswerDtoValidator(customerAnswersDto)) {
         Bundle body = bundleService.returnBundle(customerAnswersDto);
         return ResponseEntity.ok(body);
      } else {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
   }

   /**Endpoint to modify a bundle.
    *
    * @param bundleModificationDto contains bundle to modify, client answers and products to change
    * @return the modified bundle
    */
   @PostMapping(value = "/modify")
   public ResponseEntity<Bundle> modifyBundle(@RequestBody BundleModificationDto bundleModificationDto) {
      if (validateService.validateModificationBundleDto(bundleModificationDto)) {
         return ResponseEntity.ok(bundleService.modifyBundle(bundleModificationDto));
      } else {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
   }
}
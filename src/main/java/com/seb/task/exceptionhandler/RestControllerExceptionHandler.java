package com.seb.task.exceptionhandler;

import com.seb.task.constants.HomeAssignmentConstants;
import com.seb.task.entity.bundle.BundleType;
import com.seb.task.entity.product.accounts.AccountType;
import com.seb.task.entity.product.cards.CardType;
import com.seb.task.exceptions.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ControllerAdvice
public class RestControllerExceptionHandler {

   @ExceptionHandler
   @ResponseBody
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public Map<String, List<FieldErrorResponse>> handleMethodArgumentNotValid(
         MethodArgumentNotValidException exception) {
      return error(exception.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> new FieldErrorResponse(fieldError.getField(), fieldError.getDefaultMessage()))
            .collect(Collectors.toList()));
   }

   private Map<String, List<FieldErrorResponse>> error(List<FieldErrorResponse> errors) {
      return Collections.singletonMap("errors", errors);
   }

   @ExceptionHandler(value = RuntimeException.class)
   public HttpEntity<String> handleException(RuntimeException exception) {

      if (exception instanceof NoSuitableProductException) {
         return new ResponseEntity<>(
               "No suitable product available with the given answers",
               HttpStatus.BAD_REQUEST);
      }

      if (exception instanceof InvalidAgeException) {
         return new ResponseEntity<>(
               "Wrong answer was given to question : age must be within 1 and 99."
                     +" An age of " + ((InvalidAgeException) exception).getAge() + " was typed instead.",
               HttpStatus.BAD_REQUEST);
      }

      if (exception instanceof InvalidIncomeException) {
         return new ResponseEntity<>(
               "Wrong answer was given to question : income must be greater than 0." +
                     " An income of " + ((InvalidIncomeException) exception).getIncome() + " was typed instead.",
               HttpStatus.BAD_REQUEST);
      }


      if (exception instanceof InvalidBundleException) {
         List<String> enumNames = Stream.of(BundleType.values())
               .map(Enum::name)
               .collect(Collectors.toList());
         String message = "A wrong bundle was specified. Bundle has to be chosen within : " + enumNames + ".";

         return new ResponseEntity<>(
               message + " " + ((InvalidBundleException) exception).getTypedBundle() + " was typed instead.",
               HttpStatus.BAD_REQUEST);
      }

      if (exception instanceof InvalidAccountException) {
         List<String> enumNames = Stream.of(AccountType.values())
               .map(Enum::name)
               .collect(Collectors.toList());
         String message = "A wrong account type was specified. Account has to be chosen within : " + enumNames + ".";

         return new ResponseEntity<>(
               message + " " + ((InvalidAccountException) exception).getTypedAccount() + " was typed instead.",
               HttpStatus.BAD_REQUEST);
      }

      if (exception instanceof InvalidCardException) {
         List<String> enumNames = Stream.of(CardType.values())
               .map(Enum::name)
               .collect(Collectors.toList());
         String message = "A wrong card type was specified. Card has to be chosen within : " + enumNames + ".";

         return new ResponseEntity<>(
               message + " " + ((InvalidCardException) exception).getTypedCard() + " was typed instead.",
               HttpStatus.BAD_REQUEST);
      }

      if (exception instanceof ZeroIncomeException) {

         String message = "Insufficient income. This account is only allowed with an income higher than 0";

         return new ResponseEntity<>(
               message + " The current income is instead" + ((ZeroIncomeException) exception).getIncome(),
               HttpStatus.BAD_REQUEST);
      }


      if (exception instanceof HighIncomeException) {

         String message =
               "Insufficient income. The selected product is only allowed with an income higher than " + HomeAssignmentConstants.HIGH_INCOME;

         return new ResponseEntity<>(
               message + ". The current income is instead " + ((HighIncomeException) exception).getIncome(),
               HttpStatus.BAD_REQUEST);
      }


      if (exception instanceof MiddleIncomeException) {

         String message =
               "Insufficient income. This product is only allowed with an income higher than " + HomeAssignmentConstants.MIDDLE_INCOME;

         return new ResponseEntity<>(
               message + ". The current income is instead " + ((MiddleIncomeException) exception).getIncome(),
               HttpStatus.BAD_REQUEST);
      }



      if (exception instanceof UnderAgeException) {
         return new ResponseEntity<>(
               "Underage exception: to get this product is required an age higher than "
                     + HomeAssignmentConstants.AGE_SEVENTEEN
                     + " An age of " + ((UnderAgeException) exception).getAge() + " was typed instead.",
               HttpStatus.BAD_REQUEST);
      }

      if (exception instanceof OverAgeException) {
         return new ResponseEntity<>(
               "Over age exception: to get this product is required an age not higher than "
                     + HomeAssignmentConstants.AGE_EIGHTEEN
                     + ". An age of " + ((OverAgeException) exception).getAge() + " was typed instead.",
               HttpStatus.BAD_REQUEST);
      }

      if (exception instanceof IsNotStudentException) {
         return new ResponseEntity<>(
               "Is not a student exception: to get this product is required to be a student.",
               HttpStatus.BAD_REQUEST);
      }

      if (exception instanceof WeakBundleException) {
         return new ResponseEntity<>(
               "Weak bundle exception exception: is not allowed to get this product with the bundle " + BundleType.JUNIOR_SAVER,
               HttpStatus.BAD_REQUEST);
      }











      throw  exception;

   }


}

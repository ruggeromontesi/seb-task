package com.seb.task.exceptionhandler;

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
        if (exception instanceof InvalidAgeException) {
            return new ResponseEntity<>(
                    "Wrong answer was given to question : age must be within 1 and 99." +
                            " An age of " + ((InvalidAgeException) exception).getAge() + " was typed instead.",
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


        throw  exception;

    }


}

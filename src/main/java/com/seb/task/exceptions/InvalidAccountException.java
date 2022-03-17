package com.seb.task.exceptions;

public class InvalidAccountException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private  String typedAccount;

    public InvalidAccountException(String message, String typedAccount) {
        super(message);
        this.typedAccount = typedAccount;
    }

    public String getTypedAccount() {
        return typedAccount;
    }
}

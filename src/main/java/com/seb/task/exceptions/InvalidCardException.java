package com.seb.task.exceptions;

public class InvalidCardException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private  String typedCard;

    public InvalidCardException(String message, String typedCard) {
        super(message);
        this.typedCard = typedCard;
    }

    public String getTypedCard() {
        return typedCard;
    }
}

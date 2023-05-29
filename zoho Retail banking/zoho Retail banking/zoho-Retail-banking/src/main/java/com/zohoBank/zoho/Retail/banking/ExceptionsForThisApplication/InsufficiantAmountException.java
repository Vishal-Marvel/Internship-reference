package com.zohoBank.zoho.Retail.banking.ExceptionsForThisApplication;

import lombok.Data;
import lombok.RequiredArgsConstructor;

public class InsufficiantAmountException extends RuntimeException{


    public InsufficiantAmountException(String message) {
        super(message);
    }
}

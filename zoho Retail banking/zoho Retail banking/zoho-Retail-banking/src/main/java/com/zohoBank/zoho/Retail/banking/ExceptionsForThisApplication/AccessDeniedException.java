package com.zohoBank.zoho.Retail.banking.ExceptionsForThisApplication;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(String message) {
        super(message);
    }
}

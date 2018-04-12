package com.example.service;

public class InvalidAccountBalanceException extends Exception {

    public InvalidAccountBalanceException(String message){
        super(message);
    }
}

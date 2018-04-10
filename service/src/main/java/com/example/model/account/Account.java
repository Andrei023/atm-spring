package com.example.model.account;

public abstract class Account {

    private String Id; //CNP
    private String type;
    private int balance;

    public Account(String Id, String type) {
        this.Id = Id;
        this.type = type;
        this.balance = 0;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getType() {
        return this.type;
    }

    public String getId() {
        return Id;
    }

    public String toString() {
        return "Balance: " + this.balance + " " + this.type;
    }

}

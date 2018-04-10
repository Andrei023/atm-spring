package com.example.model;

import javax.validation.constraints.NotNull;

public class Transaction {

    @NotNull
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

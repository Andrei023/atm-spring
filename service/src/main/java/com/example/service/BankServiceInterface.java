package com.example.service;

import com.example.model.entity.bank.Bank;
import com.example.model.entity.client.Client;

public interface BankServiceInterface {

    public void createAccount(Bank bank, Client client);

    public void deleteAccount(Bank bank, Client client);

    public void deposit(Bank bank, Client client, int amount, String type);

    public void withdraw(Bank bank, Client client, int amount, String type);

    public String checking(Bank bank, Client client);

    public void startMonitor(Bank bank, Client client);

    public void stopMonitor(Bank bank, Client client);

}

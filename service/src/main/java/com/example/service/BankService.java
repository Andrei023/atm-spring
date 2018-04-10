package com.example.service;

import com.example.data.DataStorage;
import com.example.data.Entry;
import com.example.model.account.EuroAccount;
import com.example.model.account.RonAccount;
import com.example.model.entity.client.Client;
import com.example.model.account.Account;
import com.example.model.entity.bank.Bank;
import com.example.model.entity.irs.Irs;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BankService implements BankServiceInterface {

    private static final Logger LOGGER = Logger.getLogger(BankService.class.getName());
    private static BankService service = new BankService();
    private Irs irs = Irs.getInstance();

    private BankService() {
    }

    public static BankService getInstance() {
        return service;
    }

    public void createAccount(Bank bank, Client client) {

        DataStorage dataStorage = bank.getDataStorage();
        EuroAccount contEuro = new EuroAccount(client.getId());
        RonAccount contRon = new RonAccount(client.getId());
        Entry e = new Entry(client, contEuro, contRon);
        dataStorage.addEntry(e);
    }

    public void deleteAccount(Bank bank, Client client) {

        DataStorage dataStorage = bank.getDataStorage();
        List<Entry> toRemove = new ArrayList<>(); //use a second list instead of iterator
        List<Entry> entries;
        entries = dataStorage.getAllEntries();
        for (Entry e : entries) {
            if ((e.getClient().equals(client))) {
                if ((e.getContEuro().getBalance() == 0) && (e.getContRon().getBalance() == 0)) { //accounts balance must be 0 in order to delete
                    toRemove.add(e);
                } else {
                    LOGGER.log(Level.FINE, "Cannot delete account because balance is not 0");
                }
            }
        }

        entries.removeAll(toRemove);
        dataStorage.removeEntry(toRemove);
    }


    public void deposit(Bank bank, Client client, int amount, String type) {
        String uType = type.toUpperCase();
        takeAction(bank, client, amount, uType, "deposit");
    }


    public void withdraw(Bank bank, Client client, int amount, String type) {
        String uType = type.toUpperCase();
        takeAction(bank, client, amount, uType, "withdraw");
    }

    public String checking(Bank bank, Client client) {

        DataStorage dataStorage = bank.getDataStorage();
        Entry entry = dataStorage.getEntryByClient(client);
        if (entry != null)
            return entry.toString();
        return "";
    }


    public void startMonitor(Bank bank, Client client) {
        DataStorage dataStorage = bank.getDataStorage();
        dataStorage.addMonitoredClient(client);
    }

    public void stopMonitor(Bank bank, Client client) {
        DataStorage dataStorage = bank.getDataStorage();
        dataStorage.removeMonitoredClient(client);
    }

    private void notifyIrs(String message) {
        irs.sentMessage(message);
    }

    private void takeAction(Bank bank, Client client, int amount, String type, String action) {

        DataStorage dataStorage = bank.getDataStorage();
        Entry entry = dataStorage.getEntryByClient(client);
        if (entry != null) {
            Account accountEuro = entry.getContEuro();
            Account accountRon = entry.getContRon();
            if (type.equals("EURO")) {
                modifyBalance(client, entry, amount, action, accountEuro, dataStorage);
            } else if (type.equals("RON")) {
                modifyBalance(client, entry, amount, action, accountRon, dataStorage);
            }
        }
    }

    private void modifyBalance(Client client, Entry e, int amount, String operation, Account account, DataStorage dataStorage) {
        if (operation.equals("deposit")) {
            account.setBalance(account.getBalance() + amount);
            if (dataStorage.getAllMonitoredClients().contains(client)) {
                this.notifyIrs(e.toString());
            }
        } else if (operation.equals("withdraw")) {
            if (account.getBalance() - amount >= 1000) {
                account.setBalance(account.getBalance() - amount);
                if (dataStorage.getAllMonitoredClients().contains(client)) {
                    this.notifyIrs(e.toString());
                }
            } else {
                LOGGER.log(Level.FINE, "Account balance cannot drop below 1000");
            }

        }
    }

}

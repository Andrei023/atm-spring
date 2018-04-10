package com.example.data;

import com.example.model.account.EuroAccount;
import com.example.model.entity.client.Client;
import com.example.model.account.Account;
import com.example.model.account.RonAccount;

//Bank Cache
public class Entry {
    private Client client;
    private EuroAccount contEuro;
    private RonAccount contRon;

    public Entry(Client client, EuroAccount contEuro, RonAccount contRon) {
        this.client = client;
        this.contEuro = contEuro;
        this.contRon = contRon;
    }

    public Client getClient() {
        return this.client;
    }

    public Account getContEuro() {
        return this.contEuro;
    }

    public Account getContRon() {
        return this.contRon;
    }

    public String toString() {
        return client.toString() + " " + contEuro.toString() + " " + contRon.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        Entry other = (Entry) obj;

        if (other.getClient().equals(this.getClient())) {
            return true;
        } else {
            return false;
        }
    }
}

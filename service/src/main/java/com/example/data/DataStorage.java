package com.example.data;

import com.example.model.entity.client.Client;

import java.util.List;
import java.util.ArrayList;

public class DataStorage {

    private List<Entry> entries = new ArrayList<Entry>(); // Bank Cache = [Client, EuroAccount, RonAccount]
    private List<Client> monitorClient = new ArrayList<Client>();

    public List<Entry> getAllEntries() {
        return this.entries;
    }

    public List<Client> getAllMonitoredClients() {
        return this.monitorClient;
    }

    public void addEntry(Entry e) {
        this.entries.add(e);
    }

    public void addMonitoredClient(Client client) {
        this.monitorClient.add(client);
    }

    public Entry getEntryByClient(Client client) {
        for (Entry entryyy : entries) {

            if (entryyy.getClient().equals(client)) {
                return entryyy;
            }
        }
        return null;
    }

    public void removeMonitoredClient(Client client) {
        this.monitorClient.remove(client);
    }

    public void removeEntry(List<Entry> toRemove) {
        entries.removeAll(toRemove);
    }
}

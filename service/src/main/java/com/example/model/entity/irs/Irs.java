package com.example.model.entity.irs;

import org.springframework.stereotype.Component;

@Component
public class Irs {

    private static Irs instance = null;
    private String cache = "";

    private Irs() {

    }

    public static Irs getInstance() {
        if (instance == null) {
            instance = new Irs();
        }
        return instance;
    }

    //message from the bank
    public void sentMessage(String message) {
        this.cache += message;
        cache += "\n";
    }

    public String getCache() {
        return this.cache;
    }
}

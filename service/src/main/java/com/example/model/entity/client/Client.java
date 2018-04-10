package com.example.model.entity.client;

public class Client {

    private String Id; //CNP
    private String name;
    private String surname;


    public Client(String Id) {
        this.Id = Id;
    }

    public Client(String Id, String name, String surname) {
        this.Id = Id;
        this.name = name;
        this.surname = surname;
    }

    public String getId() {
        return this.Id;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String toString() {
        return "Id: " + this.Id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        Client other = (Client) obj;

        if (other.getId().equals(this.getId())) {
            return true;
        } else {
            return false;
        }
    }

}
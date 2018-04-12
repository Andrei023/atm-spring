package com.example.model.entity.bank;

import com.example.data.DataStorage;

public class Bank {

    private String name;
    private String swiftCode = "23";
    private String countryIndex;
    private DataStorage dataStorage;

    public Bank() {
        dataStorage = new DataStorage();
    }

    public DataStorage getDataStorage() {
        return dataStorage;
    }

    public void setDataStorage(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public String getCountryIndex() {
        return countryIndex;
    }

    public void setCountryIndex(String countryIndex) {
        this.countryIndex = countryIndex;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

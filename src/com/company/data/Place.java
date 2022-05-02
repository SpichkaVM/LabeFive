package com.company.data;

public abstract class Place {
    protected String name;
    protected int NumberOfInhabitants;

    public Place(String name, int NumberOfInhabitants){
        this.name=name;
        this.NumberOfInhabitants = NumberOfInhabitants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfInhabitants() {
        return NumberOfInhabitants;
    }

    public void setNumberOfInhabitants(int numberOfInhabitants) {
        NumberOfInhabitants = numberOfInhabitants;
    }

    public abstract String InfoType();
}

package com.cursovay.curs.core.model;

public class Nationality {
    private int id;
    private String shortNationality;
    private String fullNationality;

    public Nationality(int id, String shortNationality, String fullNationality) {
        this.id = id;
        this.shortNationality = shortNationality;
        this.fullNationality = fullNationality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortNationality() {
        return shortNationality;
    }

    public void setShortNationality(String shortNationality) {
        this.shortNationality = shortNationality;
    }

    public String getFullNationality() {
        return fullNationality;
    }

    public void setFullNationality(String fullNationality) {
        this.fullNationality = fullNationality;
    }

    @Override
    public String toString() {
        return fullNationality;
    }
}

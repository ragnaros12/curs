package com.cursovay.curs.core.model;

public class Division {
    private int id;
    private String Division;

    public Division(int id, String division) {
        this.id = id;
        Division = division;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDivision() {
        return Division;
    }

    public void setDivision(String division) {
        Division = division;
    }

    @Override
    public String toString() {
        return Division;
    }
}

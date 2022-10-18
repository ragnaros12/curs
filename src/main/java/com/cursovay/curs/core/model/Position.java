package com.cursovay.curs.core.model;

public class Position {
    private int id;
    private String position;
    private Division division;

    public Position(int id, String position, Division division) {
        this.id = id;
        this.position = position;
        this.division = division;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    @Override
    public String toString() {
        return position;
    }
}

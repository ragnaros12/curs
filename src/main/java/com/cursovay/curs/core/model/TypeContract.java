package com.cursovay.curs.core.model;

public class TypeContract {
    private int id;
    private String typeContract;

    public TypeContract(int id, String typeContract) {
        this.id = id;
        this.typeContract = typeContract;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeContract() {
        return typeContract;
    }

    public void setTypeContract(String typeContract) {
        this.typeContract = typeContract;
    }

    @Override
    public String toString() {
        return typeContract;
    }
}

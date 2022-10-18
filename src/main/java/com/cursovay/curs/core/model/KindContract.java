package com.cursovay.curs.core.model;

public class KindContract {
    private int id;
    private TypeContract typeContract;
    private String kindContract;

    public KindContract(int id, TypeContract typeContract, String kindContract) {
        this.id = id;
        this.typeContract = typeContract;
        this.kindContract = kindContract;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeContract getTypeContract() {
        return typeContract;
    }

    public void setTypeContract(TypeContract typeContract) {
        this.typeContract = typeContract;
    }

    public String getKindContract() {
        return kindContract;
    }

    public void setKindContract(String kindContract) {
        this.kindContract = kindContract;
    }

    @Override
    public String toString() {
        return kindContract;
    }
}

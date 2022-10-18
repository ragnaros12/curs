package com.cursovay.curs.core.model;

import java.sql.Date;

public class Contract {
    private int id;
    private int Number;
    private Position position;
    private Employee employee;
    private KindContract kindContract;
    private Date date;
    private int term;
    private Date dateTerm;
    private int salary;
    private boolean work;
    private double bet;
    private String reason;


    public Contract(int id, int number, Position position, Employee employee, KindContract kindContract, Date date, int term, Date dateTerm, int salary, boolean work, double bet, String reason) {
        this.id = id;
        Number = number;
        this.position = position;
        this.employee = employee;
        this.kindContract = kindContract;
        this.date = date;
        this.term = term;
        this.dateTerm = dateTerm;
        this.salary = salary;
        this.work = work;
        this.bet = bet;
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public KindContract getKindContract() {
        return kindContract;
    }

    public void setKindContract(KindContract kindContract) {
        this.kindContract = kindContract;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Date getDateTerm() {
        return dateTerm;
    }

    public void setDateTerm(Date dateTerm) {
        this.dateTerm = dateTerm;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean isWork() {
        return work;
    }

    public void setWork(boolean work) {
        this.work = work;
    }

    public double getBet() {
        return bet;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

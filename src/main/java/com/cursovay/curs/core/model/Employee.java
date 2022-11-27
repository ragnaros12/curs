package com.cursovay.curs.core.model;

import java.sql.Date;

public class Employee {
    private int id;
    private String nationality;
    private String firstName, secondName, lastName,passport,whoGet,address,Family, insuranceСertificate, kpp,phone,locationBirthDay;
    private Date birthDay, dateIssue;

    public Employee(int id, String nationality, String firstName, String secondName, String lastName, String passport, String whoGet, String address, String family, String insuranceСertificate, String kpp, String phone, String locationBirthDay, Date birthDay, Date dateIssue) {
        this.id = id;
        this.nationality = nationality;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.passport = passport;
        this.whoGet = whoGet;
        this.address = address;
        Family = family;
        this.insuranceСertificate = insuranceСertificate;
        this.kpp = kpp;
        this.phone = phone;
        this.locationBirthDay = locationBirthDay;
        this.birthDay = birthDay;
        this.dateIssue = dateIssue;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return firstName + " " + secondName + " " + lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getWhoGet() {
        return whoGet;
    }

    public void setWhoGet(String whoGet) {
        this.whoGet = whoGet;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFamily() {
        return Family;
    }

    public void setFamily(String family) {
        Family = family;
    }

    public String getInsuranceСertificate() {
        return insuranceСertificate;
    }

    public void setInsuranceСertificate(String insuranceСertificate) {
        this.insuranceСertificate = insuranceСertificate;
    }
    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocationBirthDay() {
        return locationBirthDay;
    }

    public void setLocationBirthDay(String locationBirthDay) {
        this.locationBirthDay = locationBirthDay;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Date getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(Date dateIssue) {
        this.dateIssue = dateIssue;
    }
}
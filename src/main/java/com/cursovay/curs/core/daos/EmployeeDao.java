package com.cursovay.curs.core.daos;


import com.cursovay.curs.core.Dao;
import com.cursovay.curs.core.model.Employee;
import com.cursovay.curs.core.model.Nationality;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao extends Dao<Employee> {
    Connection connection;

    public EmployeeDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Class<Employee> getType() {
        return Employee.class;
    }

    @Override
    public void onCreate() {
        try{
            connection.prepareStatement("create table Sotrudniki(Id_sotr serial PRIMARY KEY, Id_grajd integer NOT NULL,FOREIGN KEY (Id_grajd) REFERENCES Grajdanstvo (Id_grajd)  ON DELETE CASCADE,Familia varchar,Imya varchar,otchecstvo varchar, Data_rojd Date,Mesto_rojd varchar(200), passport varchar(11), Kem_vidan varchar(50),Data_vidachi Date,Adres varchar(200),Sem_pol varchar(50),Strah_svid varchar(15),inn varchar(15),kpp varchar(50),tel varchar(30));").execute();
        }
        catch (Exception e){}
    }

    @Override
    public Integer Add(Employee value) {
        try{
            PreparedStatement statement = connection.prepareStatement
                    ("INSERT INTO public.sotrudniki(id_grajd, familia, imya, otchecstvo, data_rojd, mesto_rojd, passport, kem_vidan, data_vidachi, adres, sem_pol, strah_svid, inn, kpp, tel) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING Id_sotr;");
            statement.setInt(1, value.getNationality().getId());
            statement.setString(2, value.getFirstName());
            statement.setString(3, value.getSecondName());
            statement.setString(4, value.getLastName());
            statement.setDate(5, value.getBirthDay());
            statement.setString(6, value.getLocationBirthDay());
            statement.setString(7, value.getPassport());
            statement.setString(8, value.getWhoGet());
            statement.setDate(9, value.getDateIssue());
            statement.setString(10, value.getAddress());
            statement.setString(11, value.getFamily());
            statement.setString(12, value.getInsuranceСertificate());
            statement.setString(13, value.getInn());
            statement.setString(14, value.getKpp());
            statement.setString(15, value.getPhone());
            ResultSet set = statement.executeQuery();
            set.next();
            return set.getInt(1);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * from Sotrudniki INNER JOIN public.grajdanstvo ON grajdanstvo.id_grajd = sotrudniki.id_grajd");
            ResultSet set = statement.executeQuery();
            while (set.next()){
                employees.add(new Employee(
                        set.getInt(set.findColumn("id_sotr")),
                        new Nationality(
                                set.getInt(set.findColumn("id_grajd")),
                                set.getString(set.findColumn("grajd_sokr")),
                                set.getString(set.findColumn("grajd_poln"))
                        ),
                        set.getString(set.findColumn("imya")),
                        set.getString(set.findColumn("familia")),
                        set.getString(set.findColumn("otchecstvo")),
                        set.getString(set.findColumn("passport")),
                        set.getString(set.findColumn("Kem_vidan")),
                        set.getString(set.findColumn("Adres")),
                        set.getString(set.findColumn("Sem_pol")),
                        set.getString(set.findColumn("Strah_svid")),
                        set.getString(set.findColumn("inn")),
                        set.getString(set.findColumn("kpp")),
                        set.getString(set.findColumn("tel")),
                        set.getString(set.findColumn("Mesto_rojd")),
                        set.getDate(set.findColumn("Data_rojd")),
                        set.getDate(set.findColumn("Data_vidachi"))
                ));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return employees;
    }


    @Override
    public boolean Remove(Integer t) {
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE from Sotrudniki where id_sotr = ?");
            statement.setInt(1, t);
            statement.execute();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

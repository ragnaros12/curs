package com.cursovay.curs.core.daos;


import com.cursovay.curs.core.Dao;
import com.cursovay.curs.core.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NationalityDao extends Dao<Nationality> {
    Connection connection;

    public NationalityDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Class<Nationality> getType() {
        return Nationality.class;
    }

    @Override
    public void onCreate() {
        try{
            connection.prepareStatement("create table if not exists Grajdanstvo(Id_grajd serial PRIMARY KEY,Grajd_sokr varchar(3),GRajd_poln varchar(50));").execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Nationality> getAll() {
        List<Nationality> nationalities = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * from public.grajdanstvo");
            ResultSet set = statement.executeQuery();
            while (set.next()){
                nationalities.add(new Nationality(
                        set.getInt(set.findColumn("Id_grajd")),
                        set.getString(set.findColumn("Grajd_sokr")),
                        set.getString(set.findColumn("GRajd_poln"))
                ));
            }
        }
        catch (Exception e){

        }
        return nationalities;
    }

    @Override
    public Integer Add(Nationality value) {
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO public.grajdanstvo(grajd_sokr, grajd_poln) VALUES (?, ?) RETURNING Id_grajd");
            statement.setString(1, value.getShortNationality());
            statement.setString(2, value.getFullNationality());
            ResultSet set = statement.executeQuery();
            set.next();
            return set.getInt(1);
        }
        catch (Exception e){
            return null;
        }
    }
}

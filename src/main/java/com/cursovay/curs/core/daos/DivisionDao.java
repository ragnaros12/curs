package com.cursovay.curs.core.daos;

import com.cursovay.curs.core.Dao;
import com.cursovay.curs.core.model.Division;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DivisionDao extends Dao<Division> {
    Connection connection;

    public DivisionDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void onCreate() {
        try{
            connection.prepareStatement("create table Struct_p(Id_struct_p serial PRIMARY KEY,Struct_p varchar(50))").execute();
        }
        catch (Exception e){

        }
    }

    @Override
    public Class<Division> getType() {
        return Division.class;
    }

    @Override
    public List<Division> getAll() {
        List<Division> list = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * from Struct_p");
            ResultSet set = statement.executeQuery();
            while (set.next()){
                list.add(new Division(
                        set.getInt(set.findColumn("id_struct_p")),
                        set.getString(set.findColumn("struct_p"))
                ));
            }
        }
        catch (Exception e){}
        return list;
    }

    @Override
    public Integer Add(Division value) {
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO public.struct_p(struct_p) VALUES (?) RETURNING id_struct_p");
            statement.setString(1, value.getDivision());
            ResultSet set = statement.executeQuery();
            set.next();
            return set.getInt(1);
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean Remove(Integer t) {
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE from Struct_p where Id_struct_p = ?");
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

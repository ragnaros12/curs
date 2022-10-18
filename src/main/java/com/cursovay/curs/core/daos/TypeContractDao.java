package com.cursovay.curs.core.daos;


import com.cursovay.curs.core.Dao;
import com.cursovay.curs.core.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TypeContractDao extends Dao<TypeContract> {
    Connection connection;

    public TypeContractDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Class<TypeContract> getType() {
        return TypeContract.class;
    }

    @Override
    public void onCreate() {
        try {
            PreparedStatement statement = connection.prepareStatement("Create table IF NOT EXISTS Tip_dog(Id_tip_dog serial PRIMARY KEY,Tip_dog varchar(50))");
            statement.execute();
        }
        catch (Exception e){

        }
    }

    @Override
    public Integer Add(TypeContract value) {
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Tip_dog(tip_dog) VALUES (?) RETURNING Id_tip_dog");
            statement.setString(1,value.getTypeContract());
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
    public boolean Remove(Integer integer) {
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE from tip_dog WHERE Id_tip_dog = ?");
            statement.setInt(1, integer);
            statement.execute();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public List<TypeContract> getAll() {
        ArrayList<TypeContract> list = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * from Tip_dog");
            ResultSet set = statement.executeQuery();
            while (set.next()){
                list.add(new TypeContract(
                        set.getInt(set.findColumn("Id_tip_dog")),
                        set.getString(set.findColumn("tip_dog"))
                        ));
            }
        }
        catch (Exception e){}
        return list;
    }
}

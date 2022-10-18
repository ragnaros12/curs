package com.cursovay.curs.core.daos;


import com.cursovay.curs.core.Dao;
import com.cursovay.curs.core.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PositionDao extends Dao<Position> {
    Connection connection;

    public PositionDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Class<Position> getType() {
        return Position.class;
    }

    @Override
    public void onCreate() {
        try{
            PreparedStatement statement = connection.prepareStatement("create table Doljnost(Id_dolj serial PRIMARY KEY, doljnost varchar(255), Id_struct_p integer NOT NULL, FOREIGN KEY (Id_struct_p) REFERENCES Struct_p (Id_struct_p) ON UPDATE CASCADE ON DELETE CASCADE);");
            statement.execute();
        }
        catch (Exception e){}
    }

    @Override
    public Integer Add(Position value) {
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO public.doljnost(doljnost, id_struct_p) VALUES (?, ?) RETURNING id_dolj");
            statement.setString(1, value.getPosition());
            statement.setInt(2, value.getDivision().getId());
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
    public List<Position> getAll() {
        List<Position> positions = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * from Doljnost INNER JOIN struct_p ON struct_p.id_struct_p = Doljnost.id_struct_p");
            ResultSet set = statement.executeQuery();
            while (set.next()){
                positions.add(new Position(
                        set.getInt(set.findColumn("Id_dolj")),
                        set.getString(set.findColumn("doljnost")),
                        new Division(
                                set.getInt(set.findColumn("id_struct_p")),
                                set.getString(set.findColumn("struct_p"))
                        )
                ));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return positions;
    }

    @Override
    public boolean Remove(Integer t) {
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE from doljnost where Id_dolj = ?");
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

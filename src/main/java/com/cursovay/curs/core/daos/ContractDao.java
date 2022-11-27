package com.cursovay.curs.core.daos;

import com.cursovay.curs.core.Dao;
import com.cursovay.curs.core.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContractDao extends Dao<Contract> {
    Connection connection;

    public ContractDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Class<Contract> getType() {
        return Contract.class;
    }

    @Override
    public void onCreate() {
        try{
            connection.prepareStatement
                    ("create table Dogover(id serial PRIMARY KEY, Nomer_do varchar, Id_dolj integer NOT NULL, FOREIGN KEY (Id_dolj) REFERENCES Doljnost (Id_dolj)  ON DELETE CASCADE, Id_sotr integer NOT NULL, FOREIGN KEY (Id_sotr) REFERENCES Sotrudniki (Id_sotr)  ON DELETE CASCADE, Id_vid_dog integer," +
                            " FOREIGN KEY (Id_vid_dog) REFERENCES Vid_dog (Id_vid_dog)  ON DELETE CASCADE, data_zakl Date, Srok integer, Data_rast DATE, oklad integer, Trud_dog bool, stavka real, Rast_reason varchar(200));").execute();
        }
        catch (Exception e){}
    }

    @Override
    public List<Contract> getAll() {
        List<Contract> contracts = new ArrayList<>();
        try{
            ResultSet set = connection.prepareStatement("select * from dogover INNER JOIN doljnost ON doljnost.id_dolj = dogover.id_dolj INNER JOIN sotrudniki ON sotrudniki.id_sotr = dogover.id_sotr INNER JOIN vid_dog ON vid_dog.id_vid_dog = dogover.id_vid_dog INNER JOIN tip_dog ON tip_dog.id_tip_dog = vid_dog.id_tip_dog INNER JOIN struct_p ON struct_p.id_struct_p = doljnost.id_struct_p").executeQuery();
            while (set.next()){
                contracts.add(new Contract(
                      set.getInt(set.findColumn("id")),
                        set.getInt(set.findColumn("nomer_do")),
                        new Position(
                                set.getInt(set.findColumn("id_dolj")),
                                set.getString(set.findColumn("doljnost")),
                                new Division(
                                        set.getInt(set.findColumn("id_struct_p")),
                                        set.getString(set.findColumn("struct_p"))
                                )
                        ),
                        new Employee(
                                set.getInt(set.findColumn("id_sotr")),
                                set.getString(set.findColumn("id_grajd")),
                                set.getString(set.findColumn("imya")),
                                set.getString(set.findColumn("familia")),
                                set.getString(set.findColumn("otchecstvo")),
                                set.getString(set.findColumn("passport")),
                                set.getString(set.findColumn("Kem_vidan")),
                                set.getString(set.findColumn("Adres")),
                                set.getString(set.findColumn("Sem_pol")),
                                set.getString(set.findColumn("Strah_svid")),
                                set.getString(set.findColumn("kpp")),
                                set.getString(set.findColumn("tel")),
                                set.getString(set.findColumn("Mesto_rojd")),
                                set.getDate(set.findColumn("Data_rojd")),
                                set.getDate(set.findColumn("Data_vidachi"))
                        ),
                        new KindContract(
                                set.getInt(set.findColumn("id_vid_dog")),
                                new TypeContract(
                                        set.getInt(set.findColumn("id_tip_dog")),
                                        set.getString(set.findColumn("tip_dog"))
                                ),
                                set.getString(set.findColumn("vid_dog"))
                        ),
                        set.getDate(set.findColumn("data_zakl")),
                        set.getInt(set.findColumn("srok")),
                        set.getDate(set.findColumn("data_rast")),
                        set.getInt(set.findColumn("oklad")),
                        set.getBoolean(set.findColumn("trud_dog")),
                        set.getDouble(set.findColumn("stavka")),
                        set.getString(set.findColumn("rast_reason"))
                ));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return contracts;
    }


    @Override
    public Integer Add(Contract value) {
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO public.dogover(" +
                    "nomer_do, id_dolj, id_sotr, id_vid_dog, data_zakl, srok, data_rast, oklad, trud_dog, stavka, rast_reason)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id");
            statement.setInt(1, value.getNumber());
            statement.setInt(2, value.getPosition().getId());
            statement.setInt(3, value.getEmployee().getId());
            statement.setInt(4, value.getKindContract().getId());
            statement.setDate(5, value.getDate());
            statement.setInt(6, value.getTerm());
            statement.setDate(7, value.getDateTerm());
            statement.setInt(8, value.getSalary());
            statement.setBoolean(9, value.isWork());
            statement.setDouble(10, value.getBet());
            statement.setString(11, value.getReason());
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
            PreparedStatement statement = connection.prepareStatement("DELETE from dogover where id = ?");
            statement.setInt(1, t);
            statement.execute();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}

package com.cursovay.curs.core.daos;

import com.cursovay.curs.core.Dao;
import com.cursovay.curs.core.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KindContractDao extends Dao<KindContract> {
    Connection connection;

    public KindContractDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Class<KindContract> getType() {
        return KindContract.class;
    }

    @Override
    public void onCreate() {
        try{
            connection.prepareStatement("create table IF NOT EXISTS Vid_dog(Id_vid_dog serial PRIMARY KEY,Vid_dog varchar(50),Id_tip_dog integer NOT NULL, FOREIGN KEY (Id_tip_dog) REFERENCES Tip_dog (Id_tip_dog) ON DELETE CASCADE)").execute();
        }
        catch (Exception e){}
    }

    @Override
    public List<KindContract> getAll() {
        List<KindContract> list = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.vid_dog INNER JOIN public.tip_dog ON tip_dog.id_tip_dog = vid_dog.id_tip_dog");
            ResultSet set = statement.executeQuery();
            while (set.next()){
                list.add(new KindContract(
                        set.getInt(set.findColumn("id_vid_dog")),
                        new TypeContract(
                                set.getInt(set.findColumn("id_tip_dog")),
                                set.getString(set.findColumn("tip_dog"))
                        ),
                        set.getString(set.findColumn("vid_dog"))
                ));
            }
        }
        catch (Exception e){

        }
        return list;
    }

    @Override
    public Integer Add(KindContract value) {
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO public.vid_dog(vid_dog, id_tip_dog) VALUES (?, ?) RETURNING id_vid_dog");
            statement.setString(1, value.getKindContract());
            statement.setInt(2, value.getTypeContract().getId());
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
            PreparedStatement statement = connection.prepareStatement("DELETE from vid_dog where id_vid_dog = ?");
            statement.setInt(1, t);
            statement.executeQuery();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

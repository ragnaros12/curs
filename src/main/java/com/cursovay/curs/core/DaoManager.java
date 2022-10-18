package com.cursovay.curs.core;


import java.util.ArrayList;
import java.util.List;

public class DaoManager {
    private final List<Dao> daos;
    private static DaoManager Instance;
    private DaoManager(){
        daos = new ArrayList<>();
    }

    public void addDao(Dao dao){
        dao.onCreate();
        daos.add(dao);
    }

    public <T> Dao<T>  findDao(Class<T> t){
        for (int i = 0; i < daos.size(); i++){
            Dao<Object> o = daos.get(i);
            if(o.getType().equals(t)){
                return (Dao<T>) o;
            }
        }
        return null;
    }

    public static DaoManager getInstance() {
        if(Instance == null){
            Instance = new DaoManager();
        }
        return Instance;
    }
}

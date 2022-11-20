package com.cursovay.curs.core;

import java.util.List;

public abstract class Dao<R> {
    public abstract Class<R> getType();
    public abstract void onCreate();
    public List<R> getAll(){throw new RuntimeException("");}
    public R get(Integer t) {throw new RuntimeException("");}
    public boolean Remove(Integer t){throw new RuntimeException();}
    public Integer Add(R value) {throw new RuntimeException();}
}

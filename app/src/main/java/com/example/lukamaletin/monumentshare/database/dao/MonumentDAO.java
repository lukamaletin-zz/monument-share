package com.example.lukamaletin.monumentshare.database.dao;

import com.example.lukamaletin.monumentshare.models.Monument;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class MonumentDAO extends RuntimeExceptionDao<Monument, Integer> {

    public MonumentDAO(Dao<Monument, Integer> dao) {
        super(dao);
    }
}

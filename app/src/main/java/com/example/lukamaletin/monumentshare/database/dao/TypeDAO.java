package com.example.lukamaletin.monumentshare.database.dao;

import com.example.lukamaletin.monumentshare.models.Type;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class TypeDAO extends RuntimeExceptionDao<Type, Integer> {

    public TypeDAO(Dao<Type, Integer> dao) {
        super(dao);
    }
}

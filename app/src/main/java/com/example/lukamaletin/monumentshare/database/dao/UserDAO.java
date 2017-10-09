package com.example.lukamaletin.monumentshare.database.dao;

import com.example.lukamaletin.monumentshare.models.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class UserDAO extends RuntimeExceptionDao<User, Integer> {

    public UserDAO(Dao<User, Integer> dao) {
        super(dao);
    }
}

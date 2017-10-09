package com.example.lukamaletin.monumentshare.database.dao.wrappers;

import com.example.lukamaletin.monumentshare.database.DatabaseHelper;
import com.example.lukamaletin.monumentshare.database.dao.MonumentDAO;
import com.example.lukamaletin.monumentshare.database.dao.TypeDAO;
import com.example.lukamaletin.monumentshare.database.dao.UserDAO;
import com.example.lukamaletin.monumentshare.models.Monument;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.ormlite.annotations.OrmLiteDao;

import java.util.ArrayList;
import java.util.List;

@EBean
public class MonumentDAOWrapper {

    @OrmLiteDao(helper = DatabaseHelper.class)
    MonumentDAO monumentDAO;

    @OrmLiteDao(helper = DatabaseHelper.class)
    UserDAO userDAO;

    @OrmLiteDao(helper = DatabaseHelper.class)
    TypeDAO typeDAO;

    @Bean
    UserDAOWrapper userDAOWrapper;

    public void addMonument(Monument monument) {
        monument.setUser(userDAOWrapper.getLoggedInUser());
        monumentDAO.create(monument);
    }

    public List<Monument> getAllMonuments() {
        final List<Monument> monuments = monumentDAO.queryForAll();
        refreshUsersAndTypes(monuments);
        return monuments;
    }

    public List<Monument> getUserMonuments() {
        final List<Monument> monuments = new ArrayList<>(userDAOWrapper.getLoggedInUser().getMonuments());
        refreshTypes(monuments);
        return monuments;
    }

    private void refreshUsersAndTypes(List<Monument> monuments) {
        for (Monument monument : monuments) {
            userDAO.refresh(monument.getUser());
            typeDAO.refresh(monument.getType());
        }
    }

    private void refreshTypes(List<Monument> monuments) {
        for (Monument monument : monuments) {
            typeDAO.refresh(monument.getType());
        }
    }
}

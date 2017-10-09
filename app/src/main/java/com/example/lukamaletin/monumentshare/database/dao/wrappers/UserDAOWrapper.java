package com.example.lukamaletin.monumentshare.database.dao.wrappers;

import android.util.Log;

import com.example.lukamaletin.monumentshare.database.DatabaseHelper;
import com.example.lukamaletin.monumentshare.database.dao.UserDAO;
import com.example.lukamaletin.monumentshare.models.User;
import com.example.lukamaletin.monumentshare.utils.Preferences_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.ormlite.annotations.OrmLiteDao;

import java.sql.SQLException;

@EBean
public class UserDAOWrapper {

    private static final String TAG = UserDAOWrapper.class.getSimpleName();

    @OrmLiteDao(helper = DatabaseHelper.class)
    UserDAO userDAO;

    @Pref
    Preferences_ preferences;

    public boolean authenticateUser(String email, String password) {
        User user = null;
        try {
            user = userDAO.queryBuilder().where().eq("email", email).and().eq("password", password).queryForFirst();
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        if (user != null) {
            preferences.id().put(user.getId());
            return true;
        }
        return false;
    }

    public boolean emailAvailable(String email) {
        try {
            return userDAO.queryBuilder().where().eq("email", email).countOf() == 0;
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return false;
    }

    public void registerUser(User user) {
        userDAO.create(user);
        preferences.id().put(user.getId());
    }

    public User getUserById(int id) {
        return userDAO.queryForId(id);
    }

    public User getLoggedInUser() {
        return getUserById(preferences.id().get());
    }
}

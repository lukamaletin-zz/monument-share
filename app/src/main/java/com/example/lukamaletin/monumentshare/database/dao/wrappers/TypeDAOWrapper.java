package com.example.lukamaletin.monumentshare.database.dao.wrappers;

import android.util.Log;

import com.example.lukamaletin.monumentshare.database.DatabaseHelper;
import com.example.lukamaletin.monumentshare.database.dao.TypeDAO;
import com.example.lukamaletin.monumentshare.models.Type;

import org.androidannotations.annotations.EBean;
import org.androidannotations.ormlite.annotations.OrmLiteDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@EBean
public class TypeDAOWrapper {

    private static final String TAG = TypeDAOWrapper.class.getSimpleName();

    @OrmLiteDao(helper = DatabaseHelper.class)
    TypeDAO typeDAO;

    public boolean nameAvailable(String typeName) {
        try {
            return typeDAO.queryBuilder().where().eq("typeName", typeName).countOf() == 0;
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return false;
    }

    public void addType(Type type) {
        typeDAO.create(type);
    }

    public List<Type> getTypes() {
        return typeDAO.queryForAll();
    }

    public List<String> getTypeNames() {
        List<String> typeNames = new ArrayList<>();
        for (Type type : getTypes()) {
            typeNames.add(type.getTypeName());
        }
        return typeNames;
    }

    public Type getTypeByName(String typeName) {
        Type type = null;
        try {
            type = typeDAO.queryBuilder().where().eq("typeName", typeName).queryForFirst();
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return type;
    }
}

package com.example.lukamaletin.monumentshare.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lukamaletin.monumentshare.R;
import com.example.lukamaletin.monumentshare.models.Monument;
import com.example.lukamaletin.monumentshare.models.Type;
import com.example.lukamaletin.monumentshare.models.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, context.getString(R.string.app_name) + ".db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Type.class);
            TableUtils.createTable(connectionSource, Monument.class);
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Monument.class, true);
            TableUtils.dropTable(connectionSource, Type.class, true);
            TableUtils.dropTable(connectionSource, User.class, true);
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
}

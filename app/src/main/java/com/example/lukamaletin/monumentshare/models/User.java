package com.example.lukamaletin.monumentshare.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

@DatabaseTable(tableName = "user")
public class User {

    public static final String ID_FIELD = "id";

    @DatabaseField(columnName = ID_FIELD, generatedId = true)
    private int id;

    @DatabaseField(columnName = "username", canBeNull = false)
    private String username;

    @DatabaseField(columnName = "email", canBeNull = false, unique = true)
    private String email;

    @DatabaseField(columnName = "password", canBeNull = false)
    private String password;

    @ForeignCollectionField(columnName = "monuments", foreignFieldName = Monument.USER_ID_FIELD, eager = false)
    private Collection<Monument> monuments;

    public User() {
    }

    public User(String username, String email, String password, Collection<Monument> monuments) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.monuments = monuments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Monument> getMonuments() {
        return monuments;
    }

    public void setMonuments(Collection<Monument> monuments) {
        this.monuments = monuments;
    }
}

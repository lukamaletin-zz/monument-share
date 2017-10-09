package com.example.lukamaletin.monumentshare.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "monument")
public class Monument {

    public static final String ID_FIELD = "id";
    public static final String TYPE_ID_FIELD = "type";
    public static final String USER_ID_FIELD = "user";

    @DatabaseField(columnName = ID_FIELD, generatedId = true)
    private int id;

    @DatabaseField(columnName = "title", canBeNull = false)
    private String title;

    @DatabaseField(columnName = "imageUri", canBeNull = false)
    private String imageUri;

    @DatabaseField(columnName = "description")
    private String description;

    @DatabaseField(columnName = TYPE_ID_FIELD, canBeNull = false, foreign = true)
    private Type type;

    @DatabaseField(columnName = USER_ID_FIELD, canBeNull = false, foreign = true)
    private User user;

    public Monument() {
    }

    public Monument(String title, String imageUri, String description, Type type, User user) {
        this.title = title;
        this.imageUri = imageUri;
        this.description = description;
        this.type = type;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

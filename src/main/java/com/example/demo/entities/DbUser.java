package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class DbUser {
    @BsonId
    private ObjectId id;

    private String username;
    private String password;

    public DbUser() {
    }

    public DbUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public DbUser(ObjectId id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @JsonIgnore
    public ObjectId getObjectId(){
        return id;
    }

    public String getId() {
        return id != null ? id.toHexString() : null;
    }

    public void setId(String hexId) {
        this.id = new ObjectId(hexId);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


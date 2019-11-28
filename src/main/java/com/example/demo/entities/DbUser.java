package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import java.util.Set;

public class DbUser {
    @BsonId
    private ObjectId id;

    private String username;

    @JsonIgnore
    private String password;
    private Set<String> roles;

    public DbUser() {
    }

    public DbUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.roles = Set.of("USER");
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

    public void setId(ObjectId id) {
        this.id = id;
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}


package com.example.demo.repos;

import com.example.demo.entities.DbUser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DbUserRepo extends MongoRepository <DbUser, ObjectId> {
    DbUser findDistinctFirstByUsernameIgnoreCase(String username);
}

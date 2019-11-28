package com.example.demo.repos;


import com.example.demo.entities.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRepo extends MongoRepository<Movie, ObjectId> {
    Movie findByTitle(String title);
    List<Movie> findByTitleContaining(String partOfTitle);
    List<Movie> findByImdbRating(float imdbRating);
}


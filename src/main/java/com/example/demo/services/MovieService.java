package com.example.demo.services;


import com.example.demo.entities.Movie;
import com.example.demo.repos.MovieRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    MovieRepo movieRepo;

    public List<Movie> getAllMovies(){
        return movieRepo.findAll();
    }

    public Optional<Movie> getOneMovie(ObjectId id){
        return movieRepo.findById(id);
    }

    public Movie saveMovieToDatabase(Movie movie) {
        movie.setId(new ObjectId());
        return movieRepo.save(movie);
    }

    public Optional<Movie> updateMovieInDatabase(Map replacementMovie) {
        Optional<Movie> movie = movieRepo.findById((ObjectId) replacementMovie.get("id"));
        if(movie.isPresent()){
            movie.get().replaceObject(replacementMovie);
            movieRepo.save(movie.get());
        }
        return movie;
    }

    public Pair<Long, Long> deleteAllMovies(){
        long before = movieRepo.count();
        movieRepo.deleteAll();
        long after = movieRepo.count();
        return Pair.of(before, after);
    }

    public String deleteOne(ObjectId id) {
        String removedId = "";
        try{
            removedId = movieRepo.findById(id).get().getId();
        } catch(Exception e) {}
        movieRepo.deleteById(id);
        return removedId;
    }

}

package com.example.demo.services;


import com.example.demo.entities.Movie;
import com.example.demo.repos.MovieRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepo repo;

    public List<Movie> getAllMovies(){
        return repo.findAll();
    }

    public Optional<Movie> getOneMovie(ObjectId id){
        return repo.findById(id);
    }

    public Movie saveMovieToDatabase(Movie movie) {
        movie.setId(new ObjectId());
        return save(movie);
    }

    public Optional<Movie> updateMovieInDatabase(Map replacementMovie) {
        Optional<Movie> movie = repo.findById((ObjectId) replacementMovie.get("id"));
        if(movie.isPresent()){
            movie.get().replaceObject(replacementMovie);
            movie = Optional.of(save(movie.get()));
        }
        return movie;
    }

    public Pair<Long, Long> deleteAllMovies(){
        long before = repo.count();
        repo.deleteAll();
        long after = repo.count();
        return Pair.of(before, after);
    }

    public String deleteOne(ObjectId id) {
        String removedId = "";
        try{
            removedId = repo.findById(id).get().getId();
        } catch(Exception e) {}
        repo.deleteById(id);
        return removedId;
    }

    private Movie save(Movie movie) {

        return repo.save(movie);
    }

}

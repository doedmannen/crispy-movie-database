package com.example.demo.controllers;

import com.example.demo.entities.Movie;
import com.example.demo.services.MovieService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("api/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/{id}")
    ResponseEntity<Movie> getOne(@PathVariable ObjectId id){
        Optional<Movie> movie = movieService.getOneMovie(id);
        if(movie.isPresent()){
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    ResponseEntity<List<Movie>> getall(){
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Movie> createOne(@Valid @RequestBody Movie movie){
        return new ResponseEntity<>(movieService.saveMovieToDatabase(movie), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Movie> updateOne(@PathVariable ObjectId id, @RequestBody Map<String, ? super Object> movie){
        movie.put("id", id);
        Optional<Movie> movieOptional = movieService.updateMovieInDatabase(movie);
        if(movieOptional.isPresent()){
            return new ResponseEntity<>(movieOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteOne(@PathVariable ObjectId id){
        String removedId =  movieService.deleteOne(id);
        ResponseEntity<String> response = new ResponseEntity<>("No object matched that id", HttpStatus.NOT_FOUND);
        if(!removedId.isEmpty()){
            response = new ResponseEntity<>(String.format("Object with id %s was successfully deleted", removedId), HttpStatus.OK);
        }
        return response;
    }

    @DeleteMapping
    ResponseEntity<String> deleteAll(){
        Pair<Long, Long> deleted = movieService.deleteAllMovies();
        return new ResponseEntity<>(String.format("%d objects before deletion. %d left after. ", deleted.getFirst(), deleted.getSecond()),HttpStatus.OK);
    }
}


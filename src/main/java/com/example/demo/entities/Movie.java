package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.*;
import java.util.Map;

public class Movie {

    @BsonId
    private ObjectId id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    @Pattern(regexp = ".*[a-z]+.*", flags = {Pattern.Flag.CASE_INSENSITIVE})
    private String title;

    @Min(0)
    @Max(10)
    private float imdbRating;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 2000)
    @Pattern(regexp = ".*[a-z]+.*", flags = {Pattern.Flag.CASE_INSENSITIVE})
    private String description;

    public Movie() {
        title = "";
        imdbRating = -1;
        description = "";
    }

    public Movie replaceObject(Map movie) {
        this.setTitle((String) movie.get("title"));
        this.setImdbRating((float) movie.get("imdbRating"));
        this.setDescription((String) movie.get("description"));
        return this;
    }

    public Movie(ObjectId id, String title, float imdbRating, String description) {
        this.id = id;
        this.title = title;
        this.imdbRating = imdbRating;
        this.description = description;
    }

    @JsonIgnore
    public ObjectId getHiddenId(){
        return id;
    }

    public String getId() {
        return id != null ? id.toHexString() : null;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setId(String hexId) {
        this.id = new ObjectId(hexId);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title.matches(".{1,255}") && title.matches("(?i).*[a-zA-Z]+.*")){
            this.title = title;
        }
    }

    public float getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(float imdbRating) {
        float rating = Float.parseFloat(String.format("%2.1f", imdbRating).replaceAll(",","."));
        if(rating >= 0 && rating <= 10){
            this.imdbRating = rating;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description.matches(".{1,2000}") && description.matches(".*[a-zA-Z]+.*")) {
            this.description = description;
        }
    }
}


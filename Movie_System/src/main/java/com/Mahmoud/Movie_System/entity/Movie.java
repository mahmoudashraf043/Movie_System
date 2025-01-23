package com.Mahmoud.Movie_System.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id" , updatable = false , nullable = false)
    private Long id;

    @Column(nullable = false , length = 100)
    @JsonProperty("Title")
    private String title;

    @Column(nullable = false , length = 100)
    @JsonProperty("Genre")
    private String genre;

    @Column(nullable = false , length = 50)
    @JsonProperty("imdbID")
    private String imbdid;

    @Column(nullable = false , length = 100)
    @JsonProperty("Released")
    private String releaseDate;

    @Column(length = 255)
    @JsonProperty("Plot")
    private String description;
    @Column(length = 100)
    @JsonProperty("Director")  // Add the "Director" field from JSON
    private String director;

    @Column(length = 100)
    @JsonProperty("Writer")  // Add the "Writer" field from JSON
    private String writer;

    @Column(length = 200)
    @JsonProperty("Actors")  // Add the "Actors" field from JSON
    private String actors;

    @Column(length = 100)
    @JsonProperty("Language")  // Add the "Language" field from JSON
    private String language;

    @Column(length = 50)
    @JsonProperty("Country")  // Add the "Country" field from JSON
    private String country;

    @Column(length = 100)
    @JsonProperty("Awards")  // Add the "Awards" field from JSON
    private String awards;

    @Column(length = 255)
    @JsonProperty("Poster")  // Add the "Poster" field from JSON
    private String posterUrl;


    @OneToMany(mappedBy = "movie" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Rating> ratings;



    @Column
    @JsonIgnore
    private boolean isDeleted = false;


}

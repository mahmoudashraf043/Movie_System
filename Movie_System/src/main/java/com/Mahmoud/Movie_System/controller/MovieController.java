package com.Mahmoud.Movie_System.controller;

import com.Mahmoud.Movie_System.entity.Movie;
import com.Mahmoud.Movie_System.services.MovieService;
import com.Mahmoud.Movie_System.services.RateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;
    private final RateService rateService;

    @PostMapping
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Movie> addMovie(@RequestBody String  imdbId) throws JsonProcessingException {
        return new ResponseEntity<>(movieService.addMovie(imdbId) , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public String deleteMovie(@PathVariable int  id) throws JsonProcessingException {
        return movieService.deleteMovie(id);
    }
    @GetMapping()
    public Page<Movie> getMovies(@RequestParam(defaultValue = "0") int pageNumber) throws JsonProcessingException {

        return (movieService.getMovies(pageNumber));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Movie> findMovie(@PathVariable int  id) throws JsonProcessingException {
        return new ResponseEntity<>(movieService.findMovie(id),HttpStatus.OK);
    }
    @GetMapping("/find")
    public Page<Movie> searchMovie(@RequestParam("Title") String title, @RequestParam(defaultValue = "0") int pageNumber) throws JsonProcessingException {
        return (movieService.searchMovie(title , pageNumber));
    }

    @GetMapping("/rate/{imdbid}")
    public ResponseEntity<String> rateMovie(@RequestParam int rate ,@PathVariable String imdbid) throws JsonProcessingException {
        return new ResponseEntity<>(rateService.rateMovie(rate , imdbid) , HttpStatus.OK);
    }
}

package com.Mahmoud.Movie_System.controller;

import com.Mahmoud.Movie_System.Dto.MovieResponseDto;
import com.Mahmoud.Movie_System.Dto.RegisterDto;
import com.Mahmoud.Movie_System.entity.Movie;
import com.Mahmoud.Movie_System.entity.User;
import com.Mahmoud.Movie_System.services.MovieService;
import com.Mahmoud.Movie_System.services.RateService;
import com.Mahmoud.Movie_System.services.SecurityServices;
import com.Mahmoud.Movie_System.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@EnableMethodSecurity
public class UserController {

    private final UserService userService;
    private final MovieService movieService;
    private  final RateService rateService;




    @PostMapping("/admin/create")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<RegisterDto> createAdmin(@RequestBody RegisterDto registerDto) {
        return new ResponseEntity<>(userService.createAdmin(registerDto), HttpStatus.CREATED);
    }

    @GetMapping("/movie/add/{imdbId}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Movie> addMovie(@PathVariable String  imdbId) throws JsonProcessingException {
        return new ResponseEntity<>(movieService.addMovie(imdbId) , HttpStatus.OK);
    }

    @GetMapping("/movie/delete/{imdbId}")
    @PreAuthorize("hasAuthority('admin')")
    public String deleteMovie(@PathVariable String  imdbId) throws JsonProcessingException {
        return movieService.deleteMovie(imdbId);
    }

    @GetMapping("/movies")
    public Page<Movie> getMovies(@RequestParam(defaultValue = "0") int pageNumber) throws JsonProcessingException {

        return (movieService.getMovies(pageNumber));
    }
    @GetMapping("/movies/get/{imdb}")
    public ResponseEntity<Movie> findMovie(@PathVariable String  imdbId) throws JsonProcessingException {
        return new ResponseEntity<>(movieService.findMovie(imdbId),HttpStatus.OK);
    }
    @GetMapping("movies/find")
    public Page<Movie> searchMovie(@RequestParam("Title") String title, @RequestParam(defaultValue = "0") int pageNumber) throws JsonProcessingException {
        return (movieService.searchMovie(title , pageNumber));
    }
    @GetMapping("movies/rate/{imdbid}")
    public ResponseEntity<String> rateMovie(@RequestParam int rate ,@PathVariable String imdbid) throws JsonProcessingException {
        return new ResponseEntity<>(rateService.rateMovie(rate , imdbid) , HttpStatus.OK);
    }
}
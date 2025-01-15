package com.Mahmoud.Movie_System.controller;

import com.Mahmoud.Movie_System.Dto.MovieResponseDto;
import com.Mahmoud.Movie_System.entity.Movie;
import com.Mahmoud.Movie_System.services.GetMovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableMethodSecurity
@RequiredArgsConstructor
public class GetMoviesController {


    private final GetMovieService getMovieService;



    @GetMapping("/movie/list")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<MovieResponseDto> getAllMovies(@RequestParam(required = false , defaultValue = "Batman") String title,
                                                         @RequestParam(required = false , defaultValue = "1") int pageNumber) throws JsonProcessingException {


        return new ResponseEntity<>(getMovieService.getAllMovies(title , pageNumber), HttpStatus.OK);

    }
}

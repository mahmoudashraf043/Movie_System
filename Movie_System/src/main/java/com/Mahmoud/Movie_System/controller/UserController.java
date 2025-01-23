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
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;
    private final MovieService movieService;
    private  final RateService rateService;




    @PostMapping("/admin/create")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<RegisterDto> createAdmin(@RequestBody RegisterDto registerDto) {
        return new ResponseEntity<>(userService.createAdmin(registerDto), HttpStatus.CREATED);
    }


}
package com.Mahmoud.Movie_System.controller;

import com.Mahmoud.Movie_System.Dto.LoginDto;
import com.Mahmoud.Movie_System.Dto.RegisterDto;
import com.Mahmoud.Movie_System.Dto.TokenDto;
import com.Mahmoud.Movie_System.entity.User;
import com.Mahmoud.Movie_System.services.SecurityServices;
import com.Mahmoud.Movie_System.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SecurityController {



    private final UserService userService;

    private final SecurityServices securityServices;




    @PostMapping("/login")
    public ResponseEntity <TokenDto> login(@RequestBody LoginDto loginDto) {

        return new ResponseEntity<>(securityServices.login(loginDto),HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<RegisterDto> register(@RequestBody RegisterDto registerDto) {
        return new ResponseEntity<>(securityServices.register(registerDto),HttpStatus.CREATED);
    }
}

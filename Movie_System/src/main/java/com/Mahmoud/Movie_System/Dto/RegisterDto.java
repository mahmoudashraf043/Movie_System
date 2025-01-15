package com.Mahmoud.Movie_System.Dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {


    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String email;

}

package com.Mahmoud.Movie_System.Dto;

import lombok.Data;

@Data
public class TokenDto {

    private String accessToken;
    private String refreshToken;
}

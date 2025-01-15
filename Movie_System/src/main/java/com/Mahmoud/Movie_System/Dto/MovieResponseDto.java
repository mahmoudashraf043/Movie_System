package com.Mahmoud.Movie_System.Dto;

import com.Mahmoud.Movie_System.entity.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class MovieResponseDto {

    @JsonProperty("Search")
    private List<Movie> movies;

    @JsonProperty("totalResults")
    private String totalResults;

}

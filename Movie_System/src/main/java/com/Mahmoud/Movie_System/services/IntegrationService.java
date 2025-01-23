package com.Mahmoud.Movie_System.services;

import com.Mahmoud.Movie_System.Dto.MovieResponseDto;
import com.Mahmoud.Movie_System.entity.Movie;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class IntegrationService {


    private final RestTemplate restTemplate;
    @Value("${omdb.api.key}")
    private  String omdbApiKey;
    @Value("${app.page.size}")
    private int pageSize;
    private static final String omdbUrl = "https://www.omdbapi.com/?type=movie&apikey=";


    public MovieResponseDto getAllMovies(String title , int pageNumber) throws JsonProcessingException {
        String url = UriComponentsBuilder.fromHttpUrl("http://www.omdbapi.com/")
                .queryParam("apikey", omdbApiKey)
                .queryParam("s", title) // Search query parameter
                .queryParam("page", pageNumber) // Pagination parameter
                .toUriString();
        return restTemplate.getForObject(url, MovieResponseDto.class);
    }

    public Movie getMovieByImdbId(String imdbId) {
        String url = UriComponentsBuilder.fromHttpUrl("http://www.omdbapi.com/")
                .queryParam("apikey", omdbApiKey)
                .queryParam("i", imdbId) // add the imdbId to the url
                .toUriString();
        return restTemplate.getForObject(url, Movie.class);
    }
}

package com.Mahmoud.Movie_System.services;

import com.Mahmoud.Movie_System.entity.Movie;
import com.Mahmoud.Movie_System.exceptionHandling.NotFoundException;
import com.Mahmoud.Movie_System.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final IntegrationService integrationService;
    @Value("${app.page.size}")
    private int PAGE_SIZE;


    public Movie addMovie(String imdbId){
    Movie tempMovie = integrationService.getMovieByImdbId(imdbId);
    if(tempMovie.getImbdid() == null){
        throw new NotFoundException("The movie Does Not Exist");
    }
    Optional<Movie> existMovie = movieRepository.findByimbdid(imdbId);
        if(existMovie.isPresent()){
            existMovie.get().setDeleted(false);
            return movieRepository.save(existMovie.get());
        }
        return movieRepository.save(tempMovie);

    }

    public String deleteMovie(int id) {
        Movie movie = movieRepository.findById(id).orElseThrow(
                () -> new NotFoundException("The movie Does Not Exist")
        );
        if(movie.isDeleted()){
            return "Movie Does Not Exist";
        }
        movie.setDeleted(true);
        movieRepository.save(movie);
        return ("Movie deleted successfully");
    }

    public Page<Movie> getMovies(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE);
        Page<Movie> movies = movieRepository.findAll(pageable);

        if (movies.isEmpty()) {
            throw new NotFoundException("The movie does not exist");
        }
        List<Movie> filteredMovies = movies.stream()
                .filter(movie -> !movie.isDeleted())
                .collect(Collectors.toList());

        return new PageImpl<>(filteredMovies, pageable, movies.getTotalElements());
    }


    public Movie findMovie(int id){
        Optional<Movie> tempMovie = movieRepository.findById(id);
        if(!tempMovie.isPresent()){
            throw new NotFoundException("The movie Does Not Exist");
        }
        return tempMovie.get();
    }

    public Page<Movie> searchMovie(String title , int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber,PAGE_SIZE);
        Page<Movie> moviePage = movieRepository.searchForMovie(title,pageable);
        if (moviePage.getContent().isEmpty()) {
            throw new NotFoundException("No movies found");
        }
        return moviePage;
    }

}

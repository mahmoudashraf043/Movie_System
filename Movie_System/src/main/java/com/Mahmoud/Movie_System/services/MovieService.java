package com.Mahmoud.Movie_System.services;

import com.Mahmoud.Movie_System.Dto.MovieResponseDto;
import com.Mahmoud.Movie_System.entity.Movie;
import com.Mahmoud.Movie_System.exceptionHandling.NotFoundException;
import com.Mahmoud.Movie_System.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final GetMovieService getMovieService;
    @Value("${app.page.size}")
    private int PAGE_SIZE;


    public Movie addMovie(String imdbId){
    Movie tempMovie = getMovieService.getMovieByImdbId(imdbId);
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

    public String deleteMovie(String imdbId) {
        Movie movie = movieRepository.findByimbdid(imdbId).orElseThrow(
                () -> new NotFoundException("The movie Does Not Exist")
        );
        if(movie.isDeleted()){
            return "Movie Does Not Exist";
        }
        movie.setDeleted(true);
        movieRepository.save(movie);
        return ("Movie deleted successfully");
    }

    public Page<Movie> getMovies(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber,PAGE_SIZE);

        return movieRepository.findAll(pageable);
    }

    public Movie findMovie(String imdbId){
        Optional<Movie> tempMovie = movieRepository.findByimbdid(imdbId);
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

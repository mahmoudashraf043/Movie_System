package com.Mahmoud.Movie_System.services;

import com.Mahmoud.Movie_System.Jwt.JwtUtil;
import com.Mahmoud.Movie_System.entity.Movie;
import com.Mahmoud.Movie_System.entity.Rating;
import com.Mahmoud.Movie_System.entity.User;
import com.Mahmoud.Movie_System.repository.MovieRepository;
import com.Mahmoud.Movie_System.repository.RatingRepository;
import com.Mahmoud.Movie_System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RateService {

    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;
    private final SecurityServices securityServices;
    private final UserRepository userRepository;

    public String rateMovie (int rate , String imdbId){
        String userName = securityServices.getCredentials();
        Optional<User> tempUser = userRepository.findByUsername(userName);
        Optional<Movie> tempMovie = movieRepository.findByimbdid(imdbId);
        Rating tempRate = new Rating();
        tempRate.setRating(rate);
        tempRate.setMovie(tempMovie.get());
        tempRate.setUser(tempUser.get());
        tempRate.setComment("this is the first rating");
        ratingRepository.save(tempRate);
        return "Rating Has been Done";
    }
}

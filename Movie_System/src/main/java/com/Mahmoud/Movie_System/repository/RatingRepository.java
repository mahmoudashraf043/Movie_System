package com.Mahmoud.Movie_System.repository;

import com.Mahmoud.Movie_System.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}

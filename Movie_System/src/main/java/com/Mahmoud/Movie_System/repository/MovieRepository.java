package com.Mahmoud.Movie_System.repository;

import com.Mahmoud.Movie_System.entity.Movie;
import com.Mahmoud.Movie_System.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Optional<Movie> findByimbdid(String imbdid);
    Page<Movie> findAll(Pageable pageable);

    @Query("SELECT m FROM Movie m WHERE " +
            "LOWER(m.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Movie> searchForMovie(@Param("keyword") String title, Pageable pageable);


}

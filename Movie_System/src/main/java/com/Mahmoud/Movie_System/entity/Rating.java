package com.Mahmoud.Movie_System.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id" , updatable = false, nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private Movie movie;

    @Column
    private Integer rating;

    @Column
    private String comment;
}

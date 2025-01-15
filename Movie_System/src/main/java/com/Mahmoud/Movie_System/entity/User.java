package com.Mahmoud.Movie_System.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Data
@JsonFilter("userFilter")
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false,length = 60)
    private String firstName;

    @Column(nullable = false,length = 60)
    private String lastName;

    @Column(nullable = false,length = 60 , unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,length = 100)
    private String email;

    @Column(nullable = false,length = 30)
    private String role;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Rating> ratings;

}

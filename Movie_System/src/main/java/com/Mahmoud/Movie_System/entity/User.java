package com.Mahmoud.Movie_System.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


@Entity
@Data
@Table(name = "user")
public class User  {
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
    @JsonManagedReference("UserRatingReference")
    private List<Rating> ratings;

}

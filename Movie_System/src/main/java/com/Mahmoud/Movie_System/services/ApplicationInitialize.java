package com.Mahmoud.Movie_System.services;

import com.Mahmoud.Movie_System.entity.User;
import com.Mahmoud.Movie_System.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationInitialize {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createMainAdmin() {
        String adminUserName = "mahmoud043";

        if (!userRepository.existsByUsername(adminUserName)) {
            User adminUser = new User();
                    adminUser.setFirstName("Mahmoud");
                    adminUser.setLastName("Admin");
                    adminUser.setUsername(adminUserName);
                    adminUser.setPassword(passwordEncoder.encode("123456789"));
                    adminUser.setEmail("mahmoud043@fawry.com");
                    adminUser.setRole("admin");

            userRepository.save(adminUser);
        }
    }
}

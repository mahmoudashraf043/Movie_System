package com.Mahmoud.Movie_System.configuration;

import com.Mahmoud.Movie_System.entity.User;
import com.Mahmoud.Movie_System.repository.UserRepository;
import com.Mahmoud.Movie_System.services.UserService;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class CustomConfig
{

    private  final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Movies_System")
                        .version("1.0 SNAPSHOT")
                        .description("This page lists all the REST APIs for Fawry Movies App"));
    }
//    @PostConstruct
//    public void inti(){
//        createMainAdmin();
//    }
//
//    public void createMainAdmin() {
//        String adminUserName = "mahmoud043";
//        String adminPassword = "123456789";
//
//        if (!userRepository.existsByUsername(adminUserName)) {
//            User adminUser = new User();
//                    adminUser.setFirstName("Admin");
//            adminUser.setLastName("Admin");
//                    adminUser.setUsername(adminUserName);
//                    adminUser.setPassword(passwordEncoder.encode(adminPassword));
//                    adminUser.setEmail("admin@fawry.task.com");
//                    adminUser.setRole("admin");
//
//            userRepository.save(adminUser);
//        }
    }



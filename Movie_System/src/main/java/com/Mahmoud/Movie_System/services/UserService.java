package com.Mahmoud.Movie_System.services;

import com.Mahmoud.Movie_System.Dto.RegisterDto;
import com.Mahmoud.Movie_System.Jwt.JwtUtil;
import com.Mahmoud.Movie_System.entity.User;
import com.Mahmoud.Movie_System.mapper.UserMapper;
import com.Mahmoud.Movie_System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public RegisterDto createAdmin(RegisterDto registerDto) {
        Optional<User> tempUser = userRepository.findByUsername(registerDto.getUsername());
        if(tempUser.isPresent()){
            throw new RuntimeException("User already exists");
        }
        User user = userMapper.dtoToUser(registerDto);
        user.setRole("admin");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return registerDto;

    }

}

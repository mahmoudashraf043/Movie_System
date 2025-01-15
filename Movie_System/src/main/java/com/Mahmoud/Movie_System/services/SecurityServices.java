package com.Mahmoud.Movie_System.services;

import com.Mahmoud.Movie_System.Dto.LoginDto;
import com.Mahmoud.Movie_System.Dto.RegisterDto;
import com.Mahmoud.Movie_System.Dto.TokenDto;
import com.Mahmoud.Movie_System.Jwt.JwtUtil;
import com.Mahmoud.Movie_System.entity.User;
import com.Mahmoud.Movie_System.exceptionHandling.NotFoundException;
import com.Mahmoud.Movie_System.exceptionHandling.UnauthorizeException;
import com.Mahmoud.Movie_System.exceptionHandling.WrongDataException;
import com.Mahmoud.Movie_System.mapper.UserMapper;
import com.Mahmoud.Movie_System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityServices {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public TokenDto login (LoginDto loginDto){

        User user = userRepository.findByUsername(loginDto.getUsername()).orElseThrow(() -> new NotFoundException("User not found"));
        if(!passwordEncoder.matches(loginDto.getPassword(),user.getPassword())){
            throw new WrongDataException("Wrong password");
        }
        return jwtUtil.generateToken(user);

    }

    public RegisterDto register (RegisterDto registerDto){
          Optional<User> tempUser = userRepository.findByUsername(registerDto.getUsername());
          if(tempUser.isPresent()){
              throw new RuntimeException("User already exists");
          }
          User user = userMapper.dtoToUser(registerDto);
          user.setRole("user");
          user.setPassword(passwordEncoder.encode(user.getPassword()));
          userRepository.save(user);
          return registerDto;

    }
    public String getCredentials() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return  authentication.getName();
        } else {
            throw new UnauthorizeException("UnAuthorized");
        }
    }
}

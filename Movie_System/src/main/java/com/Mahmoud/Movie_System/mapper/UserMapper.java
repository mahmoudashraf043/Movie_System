package com.Mahmoud.Movie_System.mapper;


import com.Mahmoud.Movie_System.Dto.RegisterDto;
import com.Mahmoud.Movie_System.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ObjectMapper objectMapper;

    public User dtoToUser(RegisterDto registerDto) {
        return objectMapper.convertValue(registerDto, User.class);
    }

    public RegisterDto userToDto(User user) {
        SimpleFilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("userFilter", SimpleBeanPropertyFilter.serializeAllExcept("password"));

        ObjectWriter writer = objectMapper.writer(filterProvider);
        return objectMapper.convertValue(user, RegisterDto.class);
    }
}

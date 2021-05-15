package com.example.foodapp.services;

import com.example.foodapp.dtos.UserDto;
import com.example.foodapp.models.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserEntity createUser(UserDto user);
    UserEntity getUser(String email);
    UserEntity getUserByUserId(String userId);
    UserEntity updateUser(String userId, UserEntity user);
    void deleteUser(String userId);
    List<UserDto> getUsers(int page, int limit);
}

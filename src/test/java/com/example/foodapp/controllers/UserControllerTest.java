package com.example.foodapp.controllers;

import com.example.foodapp.dtos.UserDto;
import com.example.foodapp.models.entities.RoleEntity;
import com.example.foodapp.models.entities.UserEntity;
import com.example.foodapp.models.repository.RoleRepository;
import com.example.foodapp.models.repository.UserRepository;
import com.example.foodapp.payload.request.UserDetailsRequestModel;
import com.example.foodapp.payload.response.ApiResponse;
import com.example.foodapp.payload.response.UserResponse;
import com.example.foodapp.services.Impl.UserServiceImpl;
import com.example.foodapp.services.UserService;
import com.example.foodapp.shared.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    ApiResponse apiResponse;

    @Mock
    UserResponse userResponse;

    @Mock
    UserRepository userRepository;

    @Mock
    UserService userService;

    final String USER_ID = "skadjifjidshuifos";
    UserDetailsRequestModel userDetailsRequestModel;
    UserDetailsRequestModel updateUser;
    RoleEntity roleEntity;
    UserEntity userEntity;
    final String userFirstname = "firstnameTEST";
    final String userLastname = "lastnameTEST";
    final String userEmail = "email@testmail.comTEST";
    final String userPassword = "pwdTEST";
    Page<UserEntity> usersPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        roleEntity = new RoleEntity("ROLE_USER");
        userDetailsRequestModel = new UserDetailsRequestModel();
        userDetailsRequestModel.setFirstName(userFirstname);
        userDetailsRequestModel.setLastName(userLastname);
        userDetailsRequestModel.setPassword(userPassword);
        userDetailsRequestModel.setEmail(userEmail);
        userResponse = new UserResponse();
        userResponse.setFirstName(userFirstname);
        userResponse.setLastName(userLastname);
        userResponse.setEmail(userEmail);
        userResponse.setUserId("skadjifjidshuifos");
        updateUser = new UserDetailsRequestModel();
        updateUser.setFirstName("Quang sdsfr");
        updateUser.setLastName("Dang iosad");
        userEntity = new UserEntity();
        userEntity.setUserId(USER_ID);
        userEntity.setFirstName(userFirstname);
        userEntity.setLastName(userLastname);
        userEntity.setEncryptedPassword(userPassword);
        userEntity.setEmail(userEmail);
    }

    @Test
    void createUser() {
        when(userService.createUser(any(UserDto.class))).thenReturn(userEntity);

        userResponse = userController.createUser(userDetailsRequestModel);

        assertNotNull(userResponse);
        assertEquals(userDetailsRequestModel.getFirstName(), userResponse.getFirstName());
        assertEquals(userDetailsRequestModel.getLastName(), userResponse.getLastName());
        assertEquals(userDetailsRequestModel.getEmail(), userResponse.getEmail());

    }

    @Test
    void getUser() {
        when(userService.getUserByUserId(anyString())).thenReturn(userEntity);

        UserResponse response = userController.getUser(userResponse.getUserId());
        assertNotNull(response);
        assertEquals(userResponse.getFirstName(), response.getFirstName());
        assertEquals(userResponse.getLastName(), response.getLastName());
    }


    @Test
    void updateUser() {
        when(userService.updateUser(anyString(),any(UserEntity.class))).thenReturn(userEntity);

        UserResponse response = userController.updateUser(USER_ID,updateUser);
        assertNotNull(response);
        assertEquals(userEntity.getFirstName(),response.getFirstName());
        assertEquals(userEntity.getLastName(),response.getLastName());

    }


    @Test
    void deleteUser() {
        apiResponse = userController.deleteUser(userResponse.getUserId());
        assertEquals("DELETE", apiResponse.getName());
        assertEquals("SUCCESS", apiResponse.getResult());
    }
}
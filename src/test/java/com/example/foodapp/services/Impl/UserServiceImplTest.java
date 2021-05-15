package com.example.foodapp.services.Impl;

import com.example.foodapp.models.entities.RoleEntity;
import com.example.foodapp.models.entities.UserEntity;
import com.example.foodapp.models.repository.RoleRepository;
import com.example.foodapp.models.repository.UserRepository;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    Utils utils;

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    UserService userService = new UserServiceImpl();

    final String USER_ID = "skadjifjidshuifos";
    UserEntity userEntity;
    RoleEntity roleEntity;
    Page<UserEntity> usersPage;

    final String userFirstname = "firstnameTEST";
    final String userLastname = "lastnameTEST";
    final String userEmail = "email@testmail.comTEST";
    final String userPassword = "pwdTEST";
//    final Set<RoleEntity> = [];


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        userEntity = new UserEntity();
        userEntity.setUserId(USER_ID);
        userEntity.setFirstName(userFirstname);
        userEntity.setLastName(userLastname);
        userEntity.setEncryptedPassword(userPassword);
        userEntity.setEmail(userEmail);
    }

    @Test
    final void getUser() {
        when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
        UserEntity user = userService.getUser(userEntity.getEmail());

        assertNotNull(user);
        assertEquals(user.getEmail(), userEntity.getEmail());
    }

    @Test
    void createUser() {
//        when(userRepository.findByEmail(anyString())).thenReturn(null);
//        when(bCryptPasswordEncoder.encode(anyString())).thenReturn(userPassword);
//        when(utils.generateUserId(anyInt())).thenReturn(USER_ID);
//        when(roleRepository.findByName(anyString())).thenReturn(roleEntity);
//        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
//
//        UserEntity user = userService.createUser(userEntity);
//
//        assertNotNull(user);
//        assertEquals(USER_ID, user.getUserId());
//        assertEquals(userEntity.getEmail(), user.getEmail());
//        assertEquals(userEntity.getFirstName(), user.getFirstName());
//        assertEquals(userEntity.getLastName(), user.getLastName());
//        assertNotNull(user.getUserId());
//        verify(bCryptPasswordEncoder, times(1)).encode(anyString());
//        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void getUserByUserId() {
     when( userRepository.findByUserId(anyString())).thenReturn(userEntity);

        UserEntity user = userService.getUserByUserId(USER_ID);

        assertNotNull(user);
        assertEquals(userFirstname, user.getFirstName());
    }

    @Test
    void getUser_NotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(null);

        assertThrows(UsernameNotFoundException.class,() -> {
            userService.getUser(userEmail);
        });

    }

    @Test
    void getUserByUserId_NotFound() {
        when( userRepository.findByUserId(anyString())).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () ->{
            userService.getUserByUserId(USER_ID);
        });
    }

    @Test
    void updateUser() {
        when( userRepository.findByUserId(anyString())).thenReturn(userEntity);

        userEntity.setLastName("ASdof");
        userEntity.setFirstName("Iroewp");

        UserEntity user = userService.updateUser(USER_ID,userEntity);

        assertNotNull(user);
        assertEquals("Iroewp", user.getFirstName());
        assertEquals("ASdof", user.getLastName());
    }

    @Test
    void deleteUser() {
        when( userRepository.findByUserId(anyString())).thenReturn(userEntity);
        userService.deleteUser(USER_ID);
        verify(userRepository, times(1)).delete(any(UserEntity.class));
    }

    @Test
    void getUsers() {
//        when( userRepository.findAll(PageRequest.of(1, anyInt()))).thenReturn(usersPage);
//
//        List<UserDto> returnValue1 = userService.getUsers(1, 15);
//        assertNotNull(returnValue1);

    }
}
package com.example.foodapp.services.Impl;

import com.example.foodapp.config.constant.ErrorMessages;
import com.example.foodapp.dtos.UserDto;
import com.example.foodapp.models.entities.RoleEntity;
import com.example.foodapp.models.entities.UserEntity;
import com.example.foodapp.models.repository.RoleRepository;
import com.example.foodapp.models.repository.UserRepository;
import com.example.foodapp.payload.response.UserResponse;
import com.example.foodapp.services.UserService;
import com.example.foodapp.shared.Utils;
import com.example.foodapp.exceptions.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Utils utils;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        if(userRepository.findByEmail(userDto.getEmail()) != null) throw new UserServiceException("Record already exists");
        UserEntity user = initUser(userDto);
        user.setEncryptedPassword(this.getEncryptedPass(userDto.getPassword()));
        user.setUserId(utils.generateUserId(30));
        this.addUserRole(user, "ROLE_USER");

        UserEntity savedUser = userRepository.save(user);

        //normalUserRole.getUsers().add(user);
        UserDto savedUserDto = new UserDto();
        BeanUtils.copyProperties(savedUser, savedUserDto);
        return savedUserDto;
    }

    private UserEntity initUser(UserDto userDto) {
        //validation here before create user
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }

    private String getEncryptedPass(String rawPass) {
        return bCryptPasswordEncoder.encode(rawPass);
    }

    public void addUserRole(UserEntity user, String roleName) {
        RoleEntity role = roleRepository.findByName(roleName);
        if (role == null) {
            return;
        }
        if (user.getRoles() != null) {
            user.getRoles().add(role);
        } else {
            Set<RoleEntity> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
        }
    }
    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null) throw new UsernameNotFoundException(email);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserDto returnValue = new UserDto();
        UserEntity userEntity = userRepository.findByUserId(userId);

        if(userEntity == null) throw new UsernameNotFoundException(userId);

        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) {

        UserDto returnValue = new UserDto();
        UserEntity userEntity = userRepository.findByUserId(userId);

        if(userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());

        UserEntity updateUser = userRepository.save(userEntity);
        BeanUtils.copyProperties(updateUser,returnValue);
        return returnValue;
    }

    @Override
    public void deleteUser(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);

        if(userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        for(RoleEntity roleEntity: userEntity.getRoles()){
            userEntity.removeUser(roleEntity);
        }

        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {
        List<UserDto> returnValue = new ArrayList<>();

        if(page > 0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<UserEntity> usersPage =  userRepository.findAll(pageableRequest);
        List<UserEntity> users = usersPage.getContent();

        for (UserEntity userEntity : users){
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userEntity, userDto);
            returnValue.add(userDto);
        }

        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity == null) throw new UsernameNotFoundException(email);
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(),new ArrayList<>());
    }
}

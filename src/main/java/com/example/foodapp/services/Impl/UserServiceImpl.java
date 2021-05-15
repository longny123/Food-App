package com.example.foodapp.services.Impl;

import com.example.foodapp.config.constant.ErrorMessages;
import com.example.foodapp.dtos.UserDto;
import com.example.foodapp.models.entities.RoleEntity;
import com.example.foodapp.models.entities.UserEntity;
import com.example.foodapp.models.entities.UserRole;
import com.example.foodapp.models.repository.RoleRepository;
import com.example.foodapp.models.repository.UserRepository;
import com.example.foodapp.models.repository.UserRoleRepository;
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
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private UserRoleRepository userRoleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private Utils utils;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserEntity createUser(UserDto userDto) {
        if(userRepository.findByEmail(userDto.getEmail()) != null) throw new UserServiceException("Record already exists");
        UserEntity userEntity = new UserEntity();
        RoleEntity role = roleRepository.findRoleById(2);
        roleRepository.save(role);
        BeanUtils.copyProperties(userDto, userEntity);
    // Hash password
        final String hashedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());
        userEntity.setEncryptedPassword(hashedPassword);
        userEntity.setUserId(utils.generateUserId(30));
        userRepository.save(userEntity);
        this.addUserRole(userEntity, role);

        return userEntity;
    }

//    private UserEntity initUser(UserEntity userEntity) {
//        //validation here before create user
//        UserEntity user = new UserEntity();
//        BeanUtils.copyProperties(userDto, user);
//        return user;
//    }
//
//    private String getEncryptedPass(String rawPass) {
//        return bCryptPasswordEncoder.encode(rawPass);
//    }

    public void addUserRole(UserEntity user, RoleEntity roleEntity) {
        if(user.getRoles() == null){
            UserRole list = new UserRole();
            list.setUserId(user);
            list.setRoleId(roleEntity);
            userRoleRepository.save(list);
        }
    }
    @Override
    public UserEntity getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null) throw new UsernameNotFoundException(email);

//        BeanUtils.copyProperties(userEntity, returnValue);
        userEntity.setRoles(userEntity.getRoles());
        return userEntity;
    }

    @Override
    public UserEntity getUserByUserId(String userId) {
        UserEntity returnValue = userRepository.findByUserId(userId);

        if(returnValue == null) throw new UsernameNotFoundException(userId);

        return returnValue;
    }

    @Override
    public UserEntity updateUser(String userId, UserEntity user) {

        UserEntity userEntity = userRepository.findByUserId(userId);

        if(userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());

        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public void deleteUser(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);

        if(userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

//        for(UserRole userRole: userEntity.getRoles()){
//            userEntity.removeUser(roleEntity);
//        }

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
            userDto.setRoles(userEntity.getRoles());
            returnValue.add(userDto);
        }

        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity == null) throw new UsernameNotFoundException(email);
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(),userEntity.getAuthorities());
    }
}

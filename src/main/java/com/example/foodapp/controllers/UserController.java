package com.example.foodapp.controllers;

import com.example.foodapp.config.constant.RequestStatus;
import com.example.foodapp.dtos.UserDto;
import com.example.foodapp.models.entities.RoleEntity;
import com.example.foodapp.models.entities.UserEntity;
import com.example.foodapp.models.entities.UserRole;
import com.example.foodapp.payload.request.UserDetailsRequestModel;
import com.example.foodapp.payload.response.ApiResponse;
import com.example.foodapp.payload.response.UserResponse;
import com.example.foodapp.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/{id}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
            )
    public UserResponse getUser(@PathVariable String id){
        UserResponse returnValue = new UserResponse();

        UserEntity userEntity = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userEntity,returnValue);
        Set<UserRole> roles = new HashSet<>(userEntity.getRoles());
        returnValue.setRole(roles);
        return returnValue;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserDetailsRequestModel userdetail){
        UserResponse returnValue = new UserResponse();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userdetail, userDto);

        UserEntity createUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createUser, returnValue);
        return returnValue ;
    }


    @PutMapping(path = "/{id}",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserResponse updateUser(@PathVariable String id ,@RequestBody UserDetailsRequestModel userdetail){
        UserResponse returnValue = new UserResponse();

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userdetail, userEntity);

        UserEntity updateUser = userService.updateUser(id, userEntity);
        BeanUtils.copyProperties(updateUser, returnValue);
        return returnValue ;
    }

    @DeleteMapping(path = "/{id}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ApiResponse deleteUser(@PathVariable String id){
        ApiResponse returnValue = new ApiResponse();
        returnValue.setName("DELETE");

        userService.deleteUser(id);

        returnValue.setResult(RequestStatus.SUCCESS.name());
        return returnValue;
    }
    @GetMapping( )
    public List<UserResponse> getUsers(@RequestParam(value="page", defaultValue="0") int page,
                                       @RequestParam(value="limit", defaultValue="25") int limit){
        List<UserResponse> returnValue = new ArrayList<>();

        List<UserDto> users = userService.getUsers(page, limit);

        for(UserDto userDto : users){
            UserResponse userModel = new UserResponse();
            BeanUtils.copyProperties(userDto, userModel);
            returnValue.add(userModel);
        }

        return returnValue;
    }
}

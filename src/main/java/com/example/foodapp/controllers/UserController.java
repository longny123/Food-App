package com.example.foodapp.controllers;

import com.example.foodapp.config.constant.RequestStatus;
import com.example.foodapp.config.constant.SecurityConstants;
import com.example.foodapp.dtos.UserDto;
import com.example.foodapp.payload.request.UserDetailsRequestModel;
import com.example.foodapp.payload.request.UserLoginRequestModel;
import com.example.foodapp.payload.response.ApiResponse;
import com.example.foodapp.payload.response.UserResponse;
import com.example.foodapp.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        UserDto userDto = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto,returnValue);
        return returnValue;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserDetailsRequestModel userdetail){
        UserResponse returnValue = new UserResponse();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userdetail, userDto);

        UserDto createUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createUser, returnValue);
        return returnValue ;
    }


    @PutMapping(path = "/{id}",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserResponse updateUser(@PathVariable String id ,@RequestBody UserDetailsRequestModel userdetail){
        UserResponse returnValue = new UserResponse();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userdetail, userDto);

        UserDto updateUser = userService.updateUser(id, userDto);
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

package com.example.foodapp.security;

import com.example.foodapp.SpringApplicationContext;
import com.example.foodapp.config.constant.SecurityConstants;
import com.example.foodapp.dtos.UserDto;
import com.example.foodapp.models.entities.RoleEntity;
import com.example.foodapp.models.entities.UserEntity;
import com.example.foodapp.models.entities.UserRole;
import com.example.foodapp.models.repository.UserRoleRepository;
import com.example.foodapp.payload.request.UserLoginRequestModel;
import com.example.foodapp.payload.response.UserLoginSuccessResponse;
import com.example.foodapp.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) {
        try{
//            UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
            UserLoginRequestModel creds = new ObjectMapper()
                    .readValue(req.getInputStream(),UserLoginRequestModel.class);

//            UserEntity user = userService.getUser(creds.getEmail());
//            Optional<UserRole> role = userRoleRepository.findById(user.getId());

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    creds.getEmail(),
                    creds.getPassword(),
                    new ArrayList<>()
            ));

        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException{
        String userName = ((User) auth.getPrincipal()).getUsername();

        String token = Jwts.builder()
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME ))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
                .compact();
        UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
        UserRoleRepository userRoleRepository = (UserRoleRepository) SpringApplicationContext.getBean("UserRole");
        UserEntity userEntity = userService.getUser(userName);

        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        res.addHeader("UserID", userEntity.getUserId());
        Set<UserRole> list = new HashSet<>();
        list.add();
        // TODO

        UserLoginSuccessResponse returnValue = new UserLoginSuccessResponse();
        returnValue.setJwt(SecurityConstants.TOKEN_PREFIX + token);
        returnValue.setUserId(userEntity.getUserId());
        returnValue.setRole(list);
        res.getWriter().write(returnValue.toString());
    }
}
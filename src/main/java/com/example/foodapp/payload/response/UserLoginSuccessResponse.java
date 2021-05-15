package com.example.foodapp.payload.response;

import com.example.foodapp.models.entities.RoleEntity;

import java.util.List;
import java.util.Set;

public class UserLoginSuccessResponse {
    private String Jwt;
    private String UserId;

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    private Set<String> role;

    public UserLoginSuccessResponse(String jwt, String userId) {
        Jwt = jwt;
        UserId = userId;
    }

    public UserLoginSuccessResponse() {
    }

    @Override
    public String toString() {
        return "{" +
                "\"Jwt\":\"" + Jwt + '\"' +
                ", \"UserId\":\"" + UserId + '\"' +
                ", \"Role\":\"" + role + '\"' +
                '}';
    }

    public String getJwt() {
        return Jwt;
    }

    public void setJwt(String jwt) {
        Jwt = jwt;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}

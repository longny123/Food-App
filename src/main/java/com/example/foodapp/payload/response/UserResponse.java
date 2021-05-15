package com.example.foodapp.payload.response;

import com.example.foodapp.models.entities.RoleEntity;
import com.example.foodapp.models.entities.UserRole;

import java.util.Set;

public class UserResponse {
    private String UserId;
    private String firstName;
    private String lastName;
    private String email;
    private Set<UserRole> role;

    public Set<UserRole> getRole() {
        return role;
    }

    public void setRole(Set<UserRole> role) {
        this.role = role;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

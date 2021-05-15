package com.example.foodapp.models.repository;

import com.example.foodapp.models.entities.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
}

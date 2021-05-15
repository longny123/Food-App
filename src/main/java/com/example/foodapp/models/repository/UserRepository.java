package com.example.foodapp.models.repository;

import com.example.foodapp.models.entities.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
    UserEntity findByEmail(final String email);
    UserEntity findByUserId(final String userId);
}

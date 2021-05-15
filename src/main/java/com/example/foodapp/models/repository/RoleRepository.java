package com.example.foodapp.models.repository;

import com.example.foodapp.models.entities.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    RoleEntity findRoleById(final Integer id);
    RoleEntity findByName(final String name);
}

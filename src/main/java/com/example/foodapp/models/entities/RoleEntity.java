package com.example.foodapp.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = 7326594887995861603L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false, unique = true)
    @NotEmpty
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set< UserEntity > users = new HashSet<>();

    public RoleEntity(){

    }

    public RoleEntity(String name){
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set< UserEntity > getUsers() {
        return users;
    }

    public void setUsers(Set < UserEntity > users) {
        this.users = users;
    }
}

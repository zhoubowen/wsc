package com.common.entity.user;

import com.common.enumes.Role;

import java.io.Serializable;

/**
 * Created by bowen on 16-3-27下午3:41.
 */
public class User implements Serializable {
//{"id":11, name }
//    @Id
    private Integer id;
    private String name;
    private String passwordHash;
    private Role role;

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "[id : " + id +", name : " + name + "]";
    }
}

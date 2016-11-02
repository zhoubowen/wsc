package com.common.entity.user;

import org.springframework.security.core.authority.AuthorityUtils;


/**
 * Created by bowen on 16-4-7下午11:40.
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getName(), user.getPasswordHash(), AuthorityUtils.createAuthorityList("ADMIN"));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

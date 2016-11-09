package com.security;

import com.common.entity.user.User;
import com.common.service.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by bowen on 16-4-7下午11:34.
 */
@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CurrentUserDetailsService.class);

    private final IUserService userService;

    @Autowired
    public CurrentUserDetailsService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUserByName(s);
        logger.info("...........login user : {}" , user);
        return new CurrentUser(user);
    }
}

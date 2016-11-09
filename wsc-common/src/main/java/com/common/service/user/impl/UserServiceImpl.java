package com.common.service.user.impl;

import com.common.dao.user.IUserDao;
import com.common.entity.user.User;
//import com.common.form.UserCreateForm;
import com.common.service.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * Created by bowen on 16-4-4下午3:00.
 */
@Service
public class UserServiceImpl implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private IUserDao userDao;

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public void addUser(String name) {
        userDao.insert(name);
    }



    @Override
    public User getUserById(long id) {
        return null;
    }

    @Override
    public User getUserByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public Collection<User> getAllUsers() {
        return null;
    }

//    @Override
//    public User create(UserCreateForm form) {
//        User user = new User();
//        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
//        user.setRole(form.getRole());
//        return null;
//    }
}

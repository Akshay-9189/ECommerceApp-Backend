package com.csi.service.impl;

import com.csi.dao.UserDao;
import com.csi.exception.UserNotFound;
import com.csi.model.UserInfo;
import com.csi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<UserInfo> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public UserInfo getUser(String userId) {
        return userDao.getUser(userId);
    }

    @Override
    public UserInfo saveUser(UserInfo userInfo) {
        return userDao.saveUser(userInfo);
    }

    @Override
    public UserInfo updateUser(String userId, UserInfo userInfo) {
        return userDao.updateUser(userId, userInfo);
    }

    @Override
    public void deleteUser(String userId) {
        userDao.deleteUser(userId);
    }

    @Override
    public UserInfo getByUserEmail(String userEmail) {
        return userDao.getByUserEmail(userEmail);
    }
}

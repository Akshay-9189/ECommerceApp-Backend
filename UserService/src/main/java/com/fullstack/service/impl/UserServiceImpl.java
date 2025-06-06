package com.fullstack.service.impl;

import com.fullstack.dao.UserDao;
import com.fullstack.dto.AddUpdateUserRequest;
import com.fullstack.enums.Roles;
import com.fullstack.model.UserInfo;
import com.fullstack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public UserInfo saveUser(AddUpdateUserRequest addUpdateUserRequest) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserFirstName(addUpdateUserRequest.getUserFirstName());
        userInfo.setUserLastName(addUpdateUserRequest.getUserLastName());
        userInfo.setUserContactNumber(addUpdateUserRequest.getUserContactNumber());
        userInfo.setUserEmail(addUpdateUserRequest.getUserEmail());
        userInfo.setUserPassword(passwordEncoder.encode(addUpdateUserRequest.getUserPassword()));
        userInfo.setRoles(Roles.USER);
        return userDao.saveUser(userInfo);
    }

    @Override
    public UserInfo updateUser(String userId, AddUpdateUserRequest addUpdateUserRequest) {
        return userDao.updateUser(userId, addUpdateUserRequest);
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
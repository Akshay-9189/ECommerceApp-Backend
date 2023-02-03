package com.csi.dao.impl;

import com.csi.dao.UserDao;
import com.csi.exception.UserNotFound;
import com.csi.model.UserInfo;
import com.csi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<UserInfo> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserInfo getUser(String userId) {
        return userRepo.findById(userId).orElseThrow(() -> new UserNotFound("User Not Found"));
    }

    @Override
    public UserInfo saveUser(UserInfo userInfo) {
        return userRepo.save(userInfo);
    }

    @Override
    public UserInfo updateUser(String userId, UserInfo userInfo) {
        UserInfo info = userRepo.findById(userId).orElseThrow(() -> new UserNotFound("User Not Exists"));
        info.setUserFirstName(userInfo.getUserFirstName());
        info.setUserLastName(userInfo.getUserLastName());
        info.setUserContactNumber(userInfo.getUserContactNumber());
        info.setUserEmail(userInfo.getUserEmail());
        info.setUserPassword(passwordEncoder.encode(userInfo.getUserPassword()));
        return userRepo.save(info);
    }

    @Override
    public void deleteUser(String userId) {
        UserInfo info = userRepo.findById(userId).orElseThrow(() -> new UserNotFound(" User Not Exists"));
        userRepo.delete(info);
    }

    @Override
    public UserInfo getByUserEmail(String userEmail) {
        return userRepo.findByUserEmail(userEmail);
    }
}

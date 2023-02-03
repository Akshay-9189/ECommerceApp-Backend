package com.csi.dao;

import com.csi.exception.UserNotFound;
import com.csi.model.UserInfo;

import java.util.List;

public interface UserDao {

    List<UserInfo> getAllUsers();

    UserInfo getUser(String userId);

    UserInfo saveUser(UserInfo userInfo);

    UserInfo updateUser(String userId, UserInfo userInfo);

    void deleteUser(String userId);

    UserInfo getByUserEmail(String userEmail);
}

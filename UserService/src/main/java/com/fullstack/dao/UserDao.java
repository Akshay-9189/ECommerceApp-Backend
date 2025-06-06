package com.fullstack.dao;

import com.fullstack.dto.AddUpdateUserRequest;
import com.fullstack.model.UserInfo;

import java.util.List;

public interface UserDao {

    List<UserInfo> getAllUsers();

    UserInfo getUser(String userId);

    UserInfo saveUser(UserInfo userInfo);

    UserInfo updateUser(String userId, AddUpdateUserRequest addUpdateUserRequest);

    void deleteUser(String userId);

    UserInfo getByUserEmail(String userEmail);
}

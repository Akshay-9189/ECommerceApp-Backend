package com.csi.service;

import com.csi.model.UserInfo;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface UserService {
    List<UserInfo> getAllUsers();

    @Cacheable(value = "userId")
    UserInfo getUser(String userId);

    UserInfo saveUser(UserInfo userInfo);

    UserInfo updateUser(String userId, UserInfo userInfo);

    void deleteUser(String userId);

    UserInfo getByUserEmail(String userEmail);
}

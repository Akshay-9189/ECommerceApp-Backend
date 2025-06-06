package com.fullstack.service;

import com.fullstack.dto.AddUpdateUserRequest;
import com.fullstack.model.UserInfo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface UserService {

    @Cacheable(value = "Users")
    List<UserInfo> getAllUsers();

    @Cacheable(value = "UserById")
    UserInfo getUser(String userId);

    @CacheEvict(value = {"Users", "UserById", "UserByMail"}, allEntries = true)
    UserInfo saveUser(AddUpdateUserRequest addUpdateUserRequest);

    @CacheEvict(value = {"Users", "UserById", "UserByMail"}, allEntries = true)
    UserInfo updateUser(String userId, AddUpdateUserRequest addUpdateUserRequest);

    @CacheEvict(value = {"Users", "UserById", "UserByMail"}, allEntries = true)
    void deleteUser(String userId);

    @Cacheable(value = "UserByMail")
    UserInfo getByUserEmail(String userEmail);
}
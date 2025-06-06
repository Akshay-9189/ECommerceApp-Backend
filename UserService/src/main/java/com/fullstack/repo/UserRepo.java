package com.fullstack.repo;

import com.fullstack.model.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<UserInfo, String> {
    UserInfo findByUserEmail(String userEmail);
}

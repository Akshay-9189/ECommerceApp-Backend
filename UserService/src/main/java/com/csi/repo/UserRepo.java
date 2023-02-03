package com.csi.repo;

import com.csi.model.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<UserInfo, String> {
    UserInfo findByUserEmail(String userEmail);
}

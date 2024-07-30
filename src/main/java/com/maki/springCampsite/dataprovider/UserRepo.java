package com.maki.springCampsite.dataprovider;

import com.maki.springCampsite.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User, String> {

    Optional<User> findUserByUsername(String username);

    Boolean existsByUsername(String username);
}

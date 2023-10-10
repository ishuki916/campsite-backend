package com.maki.springCampsite.dataprovider;

import com.maki.springCampsite.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepo extends ReactiveMongoRepository<User, String> {
}

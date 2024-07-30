package com.maki.springCampsite.gateway;

import com.maki.springCampsite.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserGateway {

    void insertUser(User user);

    List<User> findUsers();

    Optional<User> findUserById(String id);

    Optional<User> findUserByUsername(String username);

    Boolean existByUsername(String username);

}

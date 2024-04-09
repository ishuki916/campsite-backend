package com.maki.springCampsite.dataprovider;

import com.maki.springCampsite.domain.User;
import com.maki.springCampsite.gateway.UserGateway;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAdapter implements UserGateway {
    @NonNull
    UserRepo userRepo;

    @Override
    public void insertUser(User user) {
        userRepo.save(user);
    }

    @Override
    public List<User> findUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> findUserById(String id) {
        return userRepo.findById(id);
    }
}

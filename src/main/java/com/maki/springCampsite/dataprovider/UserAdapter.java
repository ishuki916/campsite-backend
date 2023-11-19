package com.maki.springCampsite.dataprovider;

import com.maki.springCampsite.domain.User;
import com.maki.springCampsite.gateway.UserGateway;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAdapter implements UserGateway {
    @NonNull
    UserRepo userRepo;

    @Override
    public void insertUser(User user) {
        userRepo.save(user);
    }
}

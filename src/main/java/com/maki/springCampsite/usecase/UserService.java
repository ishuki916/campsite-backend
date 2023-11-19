package com.maki.springCampsite.usecase;

import com.maki.springCampsite.domain.User;
import com.maki.springCampsite.gateway.UserGateway;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UserService {
    @NonNull
    UserGateway userGateway;

    public void insertUser(User user){
        userGateway.insertUser(user);
    }

}

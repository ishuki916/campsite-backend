package com.maki.springCampsite.usecase;

import com.maki.springCampsite.domain.User;
import com.maki.springCampsite.exception.UserException;
import com.maki.springCampsite.gateway.UserGateway;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    @NonNull
    UserGateway userGateway;

    public void insertUser(User user){
        userGateway.insertUser(user);
    }

    public List<User> findUsers(){
        return userGateway.findUsers();
    }

    public User findById(String id){
        return  userGateway.findUserById(id)
                .orElseThrow(()-> new UserException(UserException.ID_ERROR));
    }

}

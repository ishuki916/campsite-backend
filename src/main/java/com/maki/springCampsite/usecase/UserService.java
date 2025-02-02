package com.maki.springCampsite.usecase;

import com.maki.springCampsite.domain.User;
import com.maki.springCampsite.endpoints.res.LoginRes;
import com.maki.springCampsite.exception.UserException;
import com.maki.springCampsite.gateway.UserGateway;
import com.maki.springCampsite.utils.JwtTokenUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    @NonNull
    UserGateway userGateway;
    @NonNull
    PasswordEncoder passwordEncoder;
    @NonNull
    JwtTokenUtil jwtTokenUtil;

    public void insertUser(User user) {
        user.validate();
        Boolean usernameIsExisted = userGateway.existByUsername(user.getUsername());
        if (usernameIsExisted) {
            throw new UserException(UserException.USERNAME_ERROR);
        }
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userGateway.insertUser(user);
    }

    public Pair<String,String> login(User user) {
        user.validate();
        User userFromDB = userGateway.findUserByUsername(user.getUsername())
                .orElseThrow(() -> new UserException(UserException.USERNAME_ERROR));
        boolean isCorrectPassword = passwordEncoder.matches(user.getPassword(), userFromDB.getPassword());
        if (!isCorrectPassword) {
            throw new UserException(UserException.PASSWORD_ERROR);
        }

        String userId = userFromDB.getId();
        log.info("userId: {}", userId);

        return Pair.of(userId, jwtTokenUtil.generateToken(userId));
    }

    public List<User> findUsers() {
        return userGateway.findUsers();
    }

    public User findById(String id) {
        return userGateway.findUserById(id)
                .orElseThrow(() -> new UserException(UserException.ID_ERROR));
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return userGateway.findUserById(id)
                .orElseThrow(() -> new UserException(UserException.ID_ERROR));
    }
}

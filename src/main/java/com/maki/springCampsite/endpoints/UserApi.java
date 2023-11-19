package com.maki.springCampsite.endpoints;

import com.maki.springCampsite.domain.User;
import com.maki.springCampsite.usecase.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserApi {
    @NonNull
    UserService userService;


    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.insertUser(user);
    }

    @GetMapping
    public boolean findUser() {
        return true;
    }
}

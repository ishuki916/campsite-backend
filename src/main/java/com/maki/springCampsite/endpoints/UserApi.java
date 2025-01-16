package com.maki.springCampsite.endpoints;

import com.maki.springCampsite.domain.User;
import com.maki.springCampsite.endpoints.res.LoginRes;
import com.maki.springCampsite.endpoints.res.StandardRes;
import com.maki.springCampsite.usecase.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserApi {
    @NonNull
    UserService userService;

    @Operation(summary = "註冊")
    @PostMapping("/sign-up")
    public ResponseEntity<StandardRes> signUp(@RequestBody User user) {
        userService.insertUser(user);
        return ResponseEntity.ok(StandardRes.builder().result(true).build());
    }

    @Operation(summary = "登入")
    @PostMapping("/login")
    public ResponseEntity<LoginRes> login(@RequestBody User user) {
        return ResponseEntity.ok(userService.login(user));
    }

    @Operation(summary = "取得所有使用者")
    @GetMapping("/user")
    public List<User> findUsers() {
        return userService.findUsers();
    }

    @Operation(summary = "根據id取得使用者")
    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable("id") String id) {
        return userService.findById(id);
    }
}

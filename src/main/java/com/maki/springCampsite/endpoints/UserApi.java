package com.maki.springCampsite.endpoints;

import com.maki.springCampsite.domain.User;
import com.maki.springCampsite.endpoints.res.LoginRes;
import com.maki.springCampsite.endpoints.res.StandardRes;
import com.maki.springCampsite.usecase.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
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
        Pair<String, String> userIdAndToken = userService.login(user);
        return ResponseEntity.ok(LoginRes.builder()
                .token(userIdAndToken.getRight())
                .id(userIdAndToken.getLeft()).build());
    }

    @Operation(summary = "取得所有使用者")
    @GetMapping("/user")
    public ResponseEntity<List<User>> findUsers() {
        return ResponseEntity.ok(userService.findUsers());
    }

    @Operation(summary = "根據id取得使用者")
    @GetMapping("/user/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.findById(id));
    }
}

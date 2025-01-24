package com.maki.springCampsite.endpoints;

import com.maki.springCampsite.domain.User;
import com.maki.springCampsite.endpoints.res.LoginRes;
import com.maki.springCampsite.endpoints.res.StandardRes;
import com.maki.springCampsite.usecase.UserService;
import com.maki.springCampsite.utils.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserApi {
    @NonNull
    UserService userService;
    @NonNull
    JwtTokenUtil jwtTokenUtil;


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

    @Operation(summary = "OAUTH 2.0登入")
    @GetMapping("/callback")
    public ResponseEntity<LoginRes> handleGoogleLogin(OAuth2User oAuth2User) {
        String googleId = oAuth2User.getAttribute("sub"); // 獲取 Google 的唯一用戶 ID

        return ResponseEntity.ok(LoginRes.builder()
                .token(jwtTokenUtil.generateToken(googleId))
                .id(googleId).build());
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

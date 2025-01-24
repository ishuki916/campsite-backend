package com.maki.springCampsite.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maki.springCampsite.endpoints.res.LoginRes;
import com.maki.springCampsite.utils.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenUtil jwtTokenUtil;  // 用來生成 JWT 的服務


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

        String googleId = oauth2User.getAttribute("sub");  // 取得 Google 提供的用戶 ID

        ResponseEntity<LoginRes> responseEntity = new ResponseEntity<>(LoginRes.builder()
                .token(jwtTokenUtil.generateToken(googleId))
                .id(googleId).build(), HttpStatus.OK);
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseEntity.getBody()));
    }
}
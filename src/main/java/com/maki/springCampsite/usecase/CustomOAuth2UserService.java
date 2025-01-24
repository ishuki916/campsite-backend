package com.maki.springCampsite.usecase;

import com.maki.springCampsite.domain.User;
import com.maki.springCampsite.domain.constant.Auth;
import com.maki.springCampsite.domain.constant.UserSource;
import com.maki.springCampsite.gateway.UserGateway;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @NonNull
    UserGateway userGateway;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.debug("oAuth2User = {}", oAuth2User);

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String googleId = oAuth2User.getAttribute("sub");

        userGateway.findUserById(googleId).ifPresentOrElse(
                user -> {
                    log.debug("User already exists: {}", user);
                },
                () -> {
                    log.debug("User does not exist, creating new user");
                    userGateway.insertUser(User.builder()
                                    .id(googleId)
                                    .username(name)
                                    .auth(Auth.USER)
                                    .source(UserSource.GOOGLE)
                                    .email(email)
                            .build());
                }
        );

        return oAuth2User;
    }
}

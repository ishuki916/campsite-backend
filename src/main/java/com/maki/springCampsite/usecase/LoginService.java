package com.maki.springCampsite.usecase;

import com.maki.springCampsite.gateway.UserGateway;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {
    @NonNull
    UserGateway userGateway;

}

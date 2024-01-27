package com.maki.springCampsite.usecase;

import com.maki.springCampsite.domain.Campsite;
import com.maki.springCampsite.domain.User;
import com.maki.springCampsite.gateway.CampsiteGateway;
import com.maki.springCampsite.gateway.UserGateway;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CampsiteService {
    @NonNull
    CampsiteGateway campsiteGateway;

    public void execute(Campsite campsite){

    }

}

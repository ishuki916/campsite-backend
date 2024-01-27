package com.maki.springCampsite.usecase;

import com.maki.springCampsite.domain.Campsite;
import com.maki.springCampsite.endpoints.req.CreateCampsiteReq;
import com.maki.springCampsite.gateway.CampsiteGateway;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateCampsiteService {
    @NonNull
    CampsiteGateway campsiteGateway;

    public Campsite execute(CreateCampsiteReq req) {
        Campsite campsite = Campsite.of(req);
        return campsiteGateway.insertCampsite(campsite);
    }

}

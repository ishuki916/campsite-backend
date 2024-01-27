package com.maki.springCampsite.dataprovider;

import com.maki.springCampsite.domain.Campsite;
import com.maki.springCampsite.gateway.CampsiteGateway;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CampsiteAdapter implements CampsiteGateway {
    @NonNull
    private final CampsiteRepo campsiteRepo;

    @Override
    public Campsite insertCampsite(Campsite campsite) {
        return campsiteRepo.save(campsite);
    }
}

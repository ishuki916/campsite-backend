package com.maki.springCampsite.gateway;

import com.maki.springCampsite.domain.Campsite;

import java.util.List;
import java.util.Optional;

public interface CampsiteGateway {

    Campsite insertCampsite(Campsite campsite);

    Optional<Campsite> findById(String id);

    List<Campsite> findAll();

    void update(Campsite campsite);

    void deleteById(String id);

}

package com.maki.springCampsite.dataprovider;

import com.maki.springCampsite.domain.Campsite;
import com.maki.springCampsite.gateway.CampsiteGateway;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CampsiteAdapter implements CampsiteGateway {
    @NonNull
    private final CampsiteRepo campsiteRepo;

    @Override
    public Campsite insertCampsite(Campsite campsite) {
        return campsiteRepo.save(campsite);
    }

    @Override
    public List<Campsite> findAll() {
        return campsiteRepo.findAll();
    }

    @Override
    public Optional<Campsite> findById(String id) {
        return campsiteRepo.findById(id);
    }

    @Override
    public void update(Campsite campsite) {
        campsiteRepo.save(campsite);
    }

    @Override
    public void deleteById(String id) {
        campsiteRepo.deleteById(id);
    }
}

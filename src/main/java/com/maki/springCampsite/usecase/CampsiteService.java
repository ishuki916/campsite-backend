package com.maki.springCampsite.usecase;

import com.maki.springCampsite.domain.Campsite;
import com.maki.springCampsite.endpoints.req.CreateCampsiteReq;
import com.maki.springCampsite.exception.CampsiteException;
import com.maki.springCampsite.gateway.CampsiteGateway;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CampsiteService {
    @NonNull
    CampsiteGateway campsiteGateway;

    public Campsite execute(CreateCampsiteReq req) {
        Campsite campsite = Campsite.of(req);
        return campsiteGateway.insertCampsite(campsite);
    }

    public Campsite findById(String id) {
        return campsiteGateway.findById(id)
                .orElseThrow(() -> new CampsiteException(CampsiteException.ID_ERROR));
    }

    public List<Campsite> findAll() {
        return campsiteGateway.findAll();
    }

    public boolean update(String id, CreateCampsiteReq req) {
        campsiteGateway.findById(id)
                .orElseThrow(() -> new CampsiteException(CampsiteException.ID_ERROR));
        Campsite campsite = Campsite.of(req);
        campsite.setId(id);
        campsiteGateway.insertCampsite(campsite);
        return true;
    }

    public boolean delete(String id) {
        campsiteGateway.findById(id)
                .orElseThrow(() -> new CampsiteException(CampsiteException.ID_ERROR));
        campsiteGateway.deleteById(id);
        return true;
    }

}

package com.maki.springCampsite.usecase;

import com.maki.springCampsite.domain.Campsite;
import com.maki.springCampsite.endpoints.req.CreateCampsiteReq;
import com.maki.springCampsite.gateway.CampsiteGateway;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class FindCampsiteService {
    @NonNull
    CampsiteGateway campsiteGateway;

    public List<Campsite> execute(Map<String,String> paramMap) {
        return null;
    }

}

package com.maki.springCampsite.endpoints;

import com.maki.springCampsite.domain.Campsite;
import com.maki.springCampsite.endpoints.req.CreateCampsiteReq;
import com.maki.springCampsite.endpoints.res.CreateCampsiteRes;
import com.maki.springCampsite.usecase.CreateCampsiteService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/campsite")
public class CampsiteApi {

    @NonNull
    CreateCampsiteService createCampsiteService;

    @PostMapping
    public CreateCampsiteRes createCampsite(@RequestBody CreateCampsiteReq req) {
        Campsite result = createCampsiteService.execute(req);
        return CreateCampsiteRes.builder()
                .id(result.getId())
                .build();
    }

    /**
     * 多條件搜尋
     **/
    @GetMapping
    public boolean findCampsites() {
        return true;
    }

    @PutMapping("/{id}")
    public boolean updateCampsite(@PathVariable String id) {
        return true;
    }

    @DeleteMapping("/{id}")
    public boolean deleteCampsite(@PathVariable String id) {
        return true;
    }


}

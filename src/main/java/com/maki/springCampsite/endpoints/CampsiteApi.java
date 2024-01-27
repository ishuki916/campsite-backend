package com.maki.springCampsite.endpoints;

import com.maki.springCampsite.domain.Campsite;
import com.maki.springCampsite.domain.User;
import com.maki.springCampsite.endpoints.req.CreateCampsiteReq;
import com.maki.springCampsite.usecase.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/campsite")
public class CampsiteApi {

    @PostMapping
    public void createCampsite(@RequestBody CreateCampsiteReq req) {


    }

    /**多條件搜尋**/
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

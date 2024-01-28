package com.maki.springCampsite.endpoints;

import com.maki.springCampsite.domain.Campsite;
import com.maki.springCampsite.endpoints.req.CreateCampsiteReq;
import com.maki.springCampsite.endpoints.res.CreateCampsiteRes;
import com.maki.springCampsite.usecase.CreateCampsiteService;
import com.maki.springCampsite.usecase.FindCampsiteService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/campsite")
public class CampsiteApi {

    @NonNull
    private final CreateCampsiteService createCampsiteService;

    @NonNull
    private final FindCampsiteService findCampsiteService;


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
    public List<Campsite> findCampsites(@RequestParam Map<String, String> paramMap) {
        log.debug("findCampsites() paramMap = {}", paramMap);
        return findCampsiteService.execute(paramMap);
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

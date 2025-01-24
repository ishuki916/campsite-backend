package com.maki.springCampsite.endpoints;

import com.maki.springCampsite.domain.Campsite;
import com.maki.springCampsite.endpoints.req.CreateCampsiteReq;
import com.maki.springCampsite.endpoints.res.CreateCampsiteRes;
import com.maki.springCampsite.usecase.CampsiteService;
import com.maki.springCampsite.usecase.FindCampsiteService;
import io.swagger.v3.oas.annotations.Operation;
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
    private final CampsiteService campsiteService;

    @NonNull
    private final FindCampsiteService findCampsiteService;

    @Operation(summary = "新增營地")
    @PostMapping
    public CreateCampsiteRes createCampsite(@RequestBody CreateCampsiteReq req) {
        Campsite result = campsiteService.execute(req);
        return CreateCampsiteRes.builder()
                .id(result.getId())
                .build();
    }

    @Operation(summary = "多條件查詢營地")
    @GetMapping("/criteria")
    public List<Campsite> findCampsitesByCriteria(@RequestParam Map<String, String> paramMap) {
        log.debug("findCampsites() paramMap = {}", paramMap);
        return findCampsiteService.execute(paramMap);
    }

    @Operation(summary = "查詢所有營地")
    @GetMapping
    public List<Campsite> findCampsites() {
        return campsiteService.findAll();
    }

    @GetMapping("/{id}")
    public Campsite findById(@PathVariable("id") String id) {
        log.debug("findCampById() id = {}", id);
        return campsiteService.findById(id);
    }

    @Operation(summary = "更新營地")
    @PutMapping("/{id}")
    public boolean updateCampsite(@PathVariable String id,
                                  @RequestBody CreateCampsiteReq req) {
        return campsiteService.update(id, req);
    }

    @Operation(summary = "刪除營地")
    @DeleteMapping("/{id}")
    public boolean deleteCampsite(@PathVariable String id) {
        return campsiteService.delete(id);
    }


}

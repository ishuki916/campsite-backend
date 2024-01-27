package com.maki.springCampsite.endpoints.req;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class CreateCampsiteReq {

    private String id;

    private String name;

    private String address;

    private String district;

    private String trafficInfo;

    private String info;

    private BigDecimal pricePerNights;

    private Integer area;

    private List<String> facilities;

}

package com.maki.springCampsite.endpoints.req;

import com.maki.springCampsite.domain.Address;
import com.maki.springCampsite.domain.Facility;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Document
@Data
public class CreateCampsiteReq {

    private String id;

    private String name;

    private String address;

    private String trafficInfo;

    private String info;

    private BigDecimal pricePerNights;

    private Integer area;

    private List<String> facilities;

}

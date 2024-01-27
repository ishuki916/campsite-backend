package com.maki.springCampsite.domain;

import com.maki.springCampsite.endpoints.req.CreateCampsiteReq;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder
@Document
@Data
public class Campsite {

    @Id
    private String id;

    private String name;

    private Address address;

    private String trafficInfo;

    private String info;

    private BigDecimal pricePerNights;

    private Integer area;

    private List<Facility> facilities;
    public Campsite of(CreateCampsiteReq req){
        return null;
    }

    public void valid(){
        if(this.name == null || this.name.isEmpty()){
            throw new RuntimeException();
        }
    }



}

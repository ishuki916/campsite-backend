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
    public static Campsite of(CreateCampsiteReq req){
        Campsite campsite = Campsite.builder()
                .name(req.getName())
                .info(req.getInfo())
                .pricePerNights(req.getPricePerNights())
                .address(Address.of(req.getAddress(), req.getDistrict()))
                .trafficInfo(req.getTrafficInfo())
                .facilities(Facility.of(req.getFacilities()))
                .area(req.getArea())
                .build();
        campsite.valid();
        return campsite;
    }

    public void valid(){
        if(this.name == null || this.name.isEmpty()){
            throw new RuntimeException();
        }
        if(this.pricePerNights.compareTo(BigDecimal.ZERO) == 0){
            throw new RuntimeException();
        }
    }



}

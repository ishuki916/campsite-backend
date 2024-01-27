package com.maki.springCampsite.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
public class Address {

    private District district;

    private String address;

    public static Address of(String district, String address) {
        return Address.builder()
                .district(District.of(district))
                .address(address)
                .build();
    }
}

package com.maki.springCampsite.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Address {

    private District district;

    private String address;

    public static Address of(String address, String district) {
        return Address.builder()
                .district(District.of(district))
                .address(address)
                .build();
    }
}

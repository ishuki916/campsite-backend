package com.maki.springCampsite.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
public class Address {

    private String district;

    private String address;
}

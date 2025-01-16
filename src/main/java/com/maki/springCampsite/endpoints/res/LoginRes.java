package com.maki.springCampsite.endpoints.res;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginRes {
    private String id;

    private String token;
}

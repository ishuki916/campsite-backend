package com.maki.springCampsite.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Document
@Data
public class User {

    @Id
    private String id;

    private String password;

    private String username;

    private String auth;

}

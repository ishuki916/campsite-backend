package com.maki.springCampsite.domain;

import com.maki.springCampsite.domain.constant.Auth;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
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

    private Auth auth;


    public void validate(){
        if(StringUtils.isBlank(username)){
            throw new IllegalArgumentException("Username is required");
        }
        if(StringUtils.isBlank(password)){
            throw new IllegalArgumentException("Password is required");
        }
    }
}

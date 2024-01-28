package com.maki.springCampsite.domain;

import com.maki.springCampsite.exception.CampsiteException;

public enum District {
    TAIPEI, NEW_TAIPEI_CITY;

    public static District of(String district) {
        try {
            return District.valueOf(district);
        } catch (IllegalArgumentException e) {
            throw new CampsiteException(CampsiteException.DISTRICT_ERROR);
        }
    }


}

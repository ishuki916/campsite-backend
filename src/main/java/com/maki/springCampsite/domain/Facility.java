package com.maki.springCampsite.domain;

import java.util.ArrayList;
import java.util.List;

public enum Facility {

    toilet, showerRoom, garbage, wifi, manager, charge, refrigerator,
    kitchenSink, rentBike, washer, cafeBar, withPets;

    public static List<Facility> of(List<String> facilityList) {
        List<Facility> facilityEnumList = new ArrayList<>();
        for (String f : facilityList) {
            try {
                facilityEnumList.add(Facility.valueOf(f));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException();
            }
        }
        return facilityEnumList;
    }

}

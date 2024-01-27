package com.maki.springCampsite.domain;

public enum Facility {

    toilet, showerRoom, garbage, wifi, manager, charge, refrigerator,
    kitchenSink, rentBike, washer, cafeBar, withPets;

    public boolean getValue(String facility) {
        for(Facility f : Facility.values()){
            if(f.name().equals(facility)){
                return true;
            }
        }
        throw new RuntimeException();
    }

}

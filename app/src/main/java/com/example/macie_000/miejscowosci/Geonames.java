package com.example.macie_000.miejscowosci;

/**
 * Created by macie_000 on 2015-06-02.
 */
public class Geonames {
    private String postalCode;
    private String city;
    private String region;

    public Geonames(){}
    public Geonames(String postalCode){
        this.postalCode = postalCode;
    }
    public Geonames(String postalCode, String city,
                    String region){
        this.postalCode = postalCode;
        this.city = city;
        this.region = region;
    }


    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}

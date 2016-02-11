package com.cappex.dao;

/**
 * Created by jeremycurran on 2/10/16.
 */
public class College {
    private int id;
    private String name;
    private String city;
    private String state;
    private String zip;
    private int popularityLevel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public int getPopularityLevel() {
        return popularityLevel;
    }

    public void setPopularityLevel(int popularityLevel) {
        this.popularityLevel = popularityLevel;
    }
}

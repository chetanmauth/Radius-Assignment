package com.example.radiusassignment.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FacilityModel2 {

    @SerializedName("facility_id")
    private String facility_id;

    @SerializedName("name")
    private String name;

    @SerializedName("options")
    private List<FacilityOption> options;

    public FacilityModel2(String facility_id, String name, List<FacilityOption> options) {
        this.facility_id = facility_id;
        this.name = name;
        this.options = options;
    }

    public String getFacility_id() {
        return facility_id;
    }

    public void setFacility_id(String facility_id) {
        this.facility_id = facility_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FacilityOption> getOptions() {
        return options;
    }

    public void setOptions(List<FacilityOption> options) {
        this.options = options;
    }
}



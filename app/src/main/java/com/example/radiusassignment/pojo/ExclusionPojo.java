package com.example.radiusassignment.pojo;

import com.google.gson.annotations.SerializedName;

public class ExclusionPojo {

    @SerializedName("facility_id")
    String facilityId;

    @SerializedName("options_id")
    String optionsId;

    public ExclusionPojo(String facilityId, String optionsId) {
        this.facilityId = facilityId;
        this.optionsId = optionsId;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getOptionsId() {
        return optionsId;
    }

    public void setOptionsId(String optionsId) {
        this.optionsId = optionsId;
    }
}

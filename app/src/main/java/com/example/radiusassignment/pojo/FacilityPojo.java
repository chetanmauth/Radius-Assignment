package com.example.radiusassignment.pojo;

public class FacilityPojo {
    private String facilityId,facilityName, optionId, optionName, optionIcon;

    public FacilityPojo(String facilityId, String facilityName, String optionId, String optionName, String optionIcon) {
        this.facilityId = facilityId;
        this.facilityName = facilityName;
        this.optionId = optionId;
        this.optionName = optionName;
        this.optionIcon = optionIcon;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionIcon() {
        return optionIcon;
    }

    public void setOptionIcon(String optionIcon) {
        this.optionIcon = optionIcon;
    }
}

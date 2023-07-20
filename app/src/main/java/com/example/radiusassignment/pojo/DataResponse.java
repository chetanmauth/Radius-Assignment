package com.example.radiusassignment.pojo;

import java.util.ArrayList;
import java.util.List;

public class DataResponse {
    private ArrayList<FacilityModel2> facilities;
    private List<List<ExclusionPojo>> exclusions;

    public DataResponse(ArrayList<FacilityModel2> facilities, List<List<ExclusionPojo>> exclusions) {
        this.facilities = facilities;
        this.exclusions = exclusions;
    }

    public ArrayList<FacilityModel2> getFacilities() {
        return facilities;
    }

    public void setFacilities(ArrayList<FacilityModel2> facilities) {
        this.facilities = facilities;
    }

    public List<List<ExclusionPojo>> getExclusions() {
        return exclusions;
    }

    public void setExclusions(List<List<ExclusionPojo>> exclusions) {
        this.exclusions = exclusions;
    }
}

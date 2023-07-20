package com.example.radiusassignment.pojo;

import com.google.gson.annotations.SerializedName;

public class FacilityOption {

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("icon")
    public String icon;

    public FacilityOption(String id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

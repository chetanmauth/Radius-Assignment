package com.example.radiusassignment.pojo;

public class ExclusionModel {

    String selFacility_id;
    String selOptions_id;
    String setFacility_id;
    String setOptions_id;

    @Override
    public String toString() {
        return "ExclusionModel{" +
                "selFacility_id='" + selFacility_id + '\'' +
                ", selOptions_id='" + selOptions_id + '\'' +
                ", setFacility_id='" + setFacility_id + '\'' +
                ", setOptions_id='" + setOptions_id + '\'' +
                '}';
    }

    public ExclusionModel(String selFacility_id, String selOptions_id, String setFacility_id, String setOptions_id) {
        this.selFacility_id = selFacility_id;
        this.selOptions_id = selOptions_id;
        this.setFacility_id = setFacility_id;
        this.setOptions_id = setOptions_id;
    }

    public String getSelFacility_id() {
        return selFacility_id;
    }

    public void setSelFacility_id(String selFacility_id) {
        this.selFacility_id = selFacility_id;
    }

    public String getSelOptions_id() {
        return selOptions_id;
    }

    public void setSelOptions_id(String selOptions_id) {
        this.selOptions_id = selOptions_id;
    }

    public String getSetFacility_id() {
        return setFacility_id;
    }

    public void setSetFacility_id(String setFacility_id) {
        this.setFacility_id = setFacility_id;
    }

    public String getSetOptions_id() {
        return setOptions_id;
    }

    public void setSetOptions_id(String setOptions_id) {
        this.setOptions_id = setOptions_id;
    }
}

package com.example.radiusassignment.interfaces;

import com.example.radiusassignment.pojo.DataResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("iranjith4/ad-assignment/db")
    Call<DataResponse> getFacilityData();

}

package com.example.radiusassignment.repository;

import static com.example.radiusassignment.util.Constants.BASE_URL;

import com.example.radiusassignment.interfaces.ApiService;
import com.example.radiusassignment.interfaces.FacilityInteractor;
import com.example.radiusassignment.pojo.DataResponse;
import com.example.radiusassignment.pojo.ExclusionModel;
import com.example.radiusassignment.pojo.FacilityModel2;
import com.example.radiusassignment.pojo.FacilityPojo;
import com.example.radiusassignment.repository.DatabaseHelper;
import com.example.radiusassignment.repository.SharedPrefHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FacilityModel implements FacilityInteractor.Model {

    private DatabaseHelper databaseHelper;
    private SharedPrefHelper sharedPrefHelper;


    public FacilityModel(DatabaseHelper databaseHelper, SharedPrefHelper sharedPrefHelper) {
        this.databaseHelper = databaseHelper;
        this.sharedPrefHelper = sharedPrefHelper;
    }

    @Override
    public void getData(dataReceiveCallback callback) {
        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<DataResponse> call = service.getFacilityData();

        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.isSuccessful()) {
                    callback.onDataLoaded(response.body());
                } else {
                    callback.onError("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                callback.onError("Error: " + t.getMessage());
            }
        });
    }

    public interface dataReceiveCallback {

        void onDataLoaded(DataResponse response);

        void onError(String error);
    }
}

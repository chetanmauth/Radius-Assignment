package com.example.radiusassignment.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;

import com.example.radiusassignment.R;
import com.example.radiusassignment.adapter.SpinnerAdapter;
import com.example.radiusassignment.databinding.ActivityFacilitiesBinding;
import com.example.radiusassignment.interfaces.FacilityInteractor;
import com.example.radiusassignment.repository.FacilityModel;
import com.example.radiusassignment.pojo.SpinnerPojo;
import com.example.radiusassignment.presenter.FacilityPresenter;
import com.example.radiusassignment.repository.DatabaseHelper;
import com.example.radiusassignment.repository.SharedPrefHelper;

import java.util.ArrayList;

public class FacilitiesActivity extends AppCompatActivity implements FacilityInteractor.View {

    private ActivityFacilitiesBinding binding;
    private FacilityPresenter facilityPresenter;
    private SpinnerAdapter spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_facilities);

        SharedPrefHelper prefHelper = new SharedPrefHelper(this);
        DatabaseHelper databaseHelper = new DatabaseHelper(this, null, null, DatabaseHelper.DATABASE_VERSION);
        FacilityModel facilityModel = new FacilityModel(databaseHelper, prefHelper);
        facilityPresenter = new FacilityPresenter(this, facilityModel, databaseHelper, prefHelper);
        facilityPresenter.getFacilityData();

        binding.facilitiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showSelectedOption(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void setSpinner(ArrayList<SpinnerPojo> arrayList) {
        spinnerAdapter = new SpinnerAdapter(this);
        spinnerAdapter.setOptions(arrayList);
        binding.facilitiesSpinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void showSelectedOption(int position) {
        facilityPresenter.onSpinnerSet(position);
    }

    @Override
    public void setVisiblityRooms(boolean rooms, boolean noRooms) {
        if (rooms) {
            binding.cardRooms.setVisibility(View.VISIBLE);
        } else {
            binding.cardRooms.setVisibility(View.GONE);
        }

        if (noRooms) {
            binding.cardNoRooms.setVisibility(View.VISIBLE);
        } else {
            binding.cardNoRooms.setVisibility(View.GONE);
        }
    }

    @Override
    public void setVisiblityOther(boolean pool, boolean garden, boolean garage) {

        if (pool) {
            binding.cardSwimmingPool.setVisibility(View.VISIBLE);
        } else {
            binding.cardSwimmingPool.setVisibility(View.GONE);
        }
        if (garage) {
            binding.cardGarage.setVisibility(View.VISIBLE);
        } else {
            binding.cardGarage.setVisibility(View.GONE);
        }
        if (garden) {
            binding.cardgarden.setVisibility(View.VISIBLE);
        } else {
            binding.cardgarden.setVisibility(View.GONE);
        }
    }


}
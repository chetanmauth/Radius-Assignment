package com.example.radiusassignment.presenter;

import static com.example.radiusassignment.util.Constants.ONE_DAY_IN_MILLISECONS;

import com.example.radiusassignment.interfaces.FacilityInteractor;
import com.example.radiusassignment.repository.FacilityModel;
import com.example.radiusassignment.pojo.DataResponse;
import com.example.radiusassignment.pojo.ExclusionModel;
import com.example.radiusassignment.pojo.FacilityModel2;
import com.example.radiusassignment.pojo.FacilityPojo;
import com.example.radiusassignment.pojo.SpinnerPojo;
import com.example.radiusassignment.repository.DatabaseHelper;
import com.example.radiusassignment.repository.SharedPrefHelper;
import com.example.radiusassignment.ui.FacilitiesActivity;

import java.util.ArrayList;

public class FacilityPresenter implements FacilityInteractor.Presenter {
    ArrayList<FacilityPojo> facilityPojosArray;
    private FacilitiesActivity view;
    private FacilityModel model;
    private DatabaseHelper databaseHelper;
    private SharedPrefHelper sharedPrefHelper;


    public FacilityPresenter(FacilitiesActivity view, FacilityModel model, DatabaseHelper databaseHelper, SharedPrefHelper sharedPrefHelper) {
        this.view = view;
        this.model = model;
        this.databaseHelper = databaseHelper;
        this.sharedPrefHelper = sharedPrefHelper;
    }

    @Override
    public void getFacilityData() {


        //refresh once a day. (24hrs)
        if (sharedPrefHelper.getLastRefreshTimestamp() + ONE_DAY_IN_MILLISECONS < System.currentTimeMillis()) {

            model.getData(new FacilityModel.dataReceiveCallback() {
                @Override
                public void onDataLoaded(DataResponse response) {
                    ArrayList<FacilityModel2> facilityArrayList = response.getFacilities();

                    //add to database Facility
                    AddFacilityData(facilityArrayList);

                    //add to database Exclusion
                    ArrayList<ExclusionModel> exclusionArrayList = new ArrayList<>();
                    for (int i = 0; i < response.getExclusions().size(); i++) {
                        ExclusionModel pojo = new ExclusionModel(response.getExclusions().get(i).get(0).getFacilityId(),
                                response.getExclusions().get(i).get(0).getOptionsId(),
                                response.getExclusions().get(i).get(1).getFacilityId(),
                                response.getExclusions().get(i).get(1).getOptionsId());
                        exclusionArrayList.add(pojo);
                    }
                    AddExclusionsData(exclusionArrayList);
                    sharedPrefHelper.saveLastRefreshTimestamp(System.currentTimeMillis());
                    setView();
                }

                @Override
                public void onError(String error) {

                }
            });
        } else {
            System.out.println("else");
            setView();
        }
    }

    //set the spinner
    private void setView() {
        facilityPojosArray = databaseHelper.getFacilityData();
        ArrayList<SpinnerPojo> spinnerArray = new ArrayList<>();
        for (int i = 0; i < facilityPojosArray.size(); i++) {
            if (facilityPojosArray.get(i).getFacilityId().equals("1")) {
                spinnerArray.add(new SpinnerPojo(facilityPojosArray.get(i).getOptionName(), facilityPojosArray.get(i).getOptionIcon()));
            }
        }
        view.setSpinner(spinnerArray);
    }

    @Override
    public void AddExclusionsData(ArrayList<ExclusionModel> exclusionArrayList) {
        databaseHelper.AddExclusionsData(exclusionArrayList);
    }

    @Override
    public void AddFacilityData(ArrayList<FacilityModel2> facilityArrayList) {
        databaseHelper.AddFacilityData(facilityArrayList);
    }

    @Override
    public void loadOptions() {

    }

    @Override
    public void onSpinnerSet(int position) {

        boolean garage = true, room = true, noroom = true, pool = true, garden = true;
        int ss = Integer.parseInt(facilityPojosArray.get(position).getOptionId());
        ArrayList<ExclusionModel> exList = databaseHelper.getExclusionData();

        for (int i = 0; i < exList.size(); i++) {
            int sel = Integer.parseInt(exList.get(i).getSelOptions_id());
            int optionSet = Integer.parseInt(exList.get(i).getSetFacility_id());

            //System.out.println("posss " + position + " " + sel + " " + optionSet + " " + ss);
            if (ss == sel) {

                //System.out.println("equal " + ss);
                for (int j = 0; j < facilityPojosArray.size(); j++) {

                   // System.out.println("--- " + facilityPojosArray.get(j).getFacilityId() + " - " + (optionSet + 1));
                    if (facilityPojosArray.get(j).getFacilityId().equals("" + (optionSet))) {
                        String dis = facilityPojosArray.get(j).getOptionName().toLowerCase();
                        System.out.println("dis: " + dis);
                        if (dis.equals("garage")) {
                            garage = false;
                            break;
                        }
                        if (dis.equals("Garden Area")) {
                            garden = false;
                            break;
                        }
                        if (dis.equals("Swimming Pool")) {
                            pool = false;
                            break;
                        }
                        if (dis.equals("No Rooms")) {
                            noroom = false;
                            break;
                        }
                        if (dis.equals("1 to 3 Rooms")) {
                            room = false;
                            break;
                        }
                    }
                }
                break;
            }
        }

        view.setVisiblityOther(pool, garden, garage);
        view.setVisiblityRooms(room,noroom);
    }
}
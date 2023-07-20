package com.example.radiusassignment.interfaces;

import com.example.radiusassignment.repository.FacilityModel;
import com.example.radiusassignment.pojo.ExclusionModel;
import com.example.radiusassignment.pojo.FacilityModel2;
import com.example.radiusassignment.pojo.FacilityPojo;
import com.example.radiusassignment.pojo.SpinnerPojo;

import java.util.ArrayList;

public class FacilityInteractor {

    public interface View {

        void setSpinner(ArrayList<SpinnerPojo> arrayList);

        void showSelectedOption(int position);

        void setVisiblityRooms(boolean rooms, boolean noRooms);

        void setVisiblityOther(boolean pool, boolean garden,boolean garage);
    }

    public interface Presenter {

        void getFacilityData();

        void AddExclusionsData(ArrayList<ExclusionModel> arrayList);

        void AddFacilityData(ArrayList<FacilityModel2> arrayList);

        void onSpinnerSet(int position);

    }

    public interface Model {

        void getData(FacilityModel.dataReceiveCallback callback);


    }

    public interface PreferencesHelper {

        void saveLastRefreshTimestamp(long timestamp);

        long getLastRefreshTimestamp();
    }


    public interface DatabaseHelper {
        boolean AddExclusionsData(ArrayList<ExclusionModel> arrayList);

        boolean AddFacilityData(ArrayList<FacilityModel2> arrayList);

        ArrayList<FacilityPojo> getFacilityData();
    }

}

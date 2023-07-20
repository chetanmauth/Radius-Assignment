package com.example.radiusassignment.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.radiusassignment.interfaces.FacilityInteractor;

public class SharedPrefHelper implements FacilityInteractor.PreferencesHelper {

    private static final String PREF_NAME = "Preferences";
    private static final String LAST_REFRESH_TIMESTAMP = "last_refresh_timestamp";

    private SharedPreferences preferences;

    public SharedPrefHelper(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void saveLastRefreshTimestamp(long timestamp) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(LAST_REFRESH_TIMESTAMP, timestamp);
        editor.apply();
    }

    @Override
    public long getLastRefreshTimestamp() {
        return preferences.getLong(LAST_REFRESH_TIMESTAMP, 0);
    }
}

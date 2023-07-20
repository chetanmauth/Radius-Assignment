package com.example.radiusassignment.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.radiusassignment.pojo.ExclusionModel;
import com.example.radiusassignment.pojo.FacilityModel2;
import com.example.radiusassignment.pojo.FacilityPojo;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "myDatabase.db";

    //table facilities
    private static final String TABLE_FACILITIES = "facilities";

    private static final String COLUMN_FID = "facilitiesId";
    private static final String COLUMN_FNAME = "facilitiesName";

    private static final String COLUMN_OPT_ID = "optionID";
    private static final String COLUMN_OPT_NAME = "optionName";
    private static final String COLUMN_OPT_ICON = "optionIcon";

    //table exclusions
    private static final String TABLE_EXCLUSION = "exclusions";
    private static final String COLUMN_EX_FID_SELECT = "selFacilityId";
    private static final String COLUMN_EX_OPTID_SELECT = "selOptionsId";
    private static final String COLUMN_EX_FID_SET = "setFacilityId";
    private static final String COLUMN_EX_OPTID_SET = "setOptionsId";


    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String facilitiesQuery = "CREATE TABLE " + TABLE_FACILITIES + "(" +
                COLUMN_FID + " INTEGER," +
                COLUMN_FNAME + " TEXT," +
                COLUMN_OPT_ID + " INTEGER, " +
                COLUMN_OPT_NAME + " TEXT, " +
                COLUMN_OPT_ICON + " TEXT " +
                ")";

        String exclusionsQuery = "CREATE TABLE " + TABLE_EXCLUSION + "(" +
                COLUMN_EX_FID_SELECT + " INTEGER," +
                COLUMN_EX_OPTID_SELECT + " INTEGER, " +
                COLUMN_EX_FID_SET + " INTEGER," +
                COLUMN_EX_OPTID_SET + " INTEGER " +
                ")";


        db.execSQL(facilitiesQuery);
        db.execSQL(exclusionsQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @SuppressLint("Range")
    public ArrayList<FacilityPojo> getFacilityData() {
        String query = "Select * FROM " + TABLE_FACILITIES;
        System.out.println("query : " + query);
        SQLiteDatabase db = this.getReadableDatabase();
        System.out.println("db: " + this.getReadableDatabase());
        Cursor cursor = db.rawQuery(query, null);
        System.out.println("col Count: " + cursor.getCount());
        ArrayList<FacilityPojo> results = new ArrayList<>();

        while (cursor.moveToNext()) {
            String facilityId = cursor.getString(cursor.getColumnIndex(COLUMN_FID));
            String facilityName = cursor.getString(cursor.getColumnIndex(COLUMN_FNAME));
            String optionId = cursor.getString(cursor.getColumnIndex(COLUMN_OPT_ID));
            String optionName = cursor.getString(cursor.getColumnIndex(COLUMN_OPT_NAME));
            String optionIcon = cursor.getString(cursor.getColumnIndex(COLUMN_OPT_ICON));

            results.add(new FacilityPojo(facilityId, facilityName, optionId, optionName, optionIcon));
        }
        db.close();
        cursor.close();
        return results;
    }

    @SuppressLint("Range")
    public ArrayList<ExclusionModel> getExclusionData() {
        String query = "Select * FROM " + TABLE_EXCLUSION;
        System.out.println("query : " + query);
        SQLiteDatabase db = this.getReadableDatabase();
        System.out.println("db: " + this.getReadableDatabase());
        Cursor cursor = db.rawQuery(query, null);
        System.out.println("col Count: " + cursor.getCount());
        ArrayList<ExclusionModel> results = new ArrayList<>();

        while (cursor.moveToNext()) {
            String selFacility_id = cursor.getString(cursor.getColumnIndex(COLUMN_EX_FID_SELECT));
            String selOptions_id = cursor.getString(cursor.getColumnIndex(COLUMN_EX_OPTID_SELECT));
            String setFacility_id = cursor.getString(cursor.getColumnIndex(COLUMN_EX_FID_SET));
            String setOptions_id = cursor.getString(cursor.getColumnIndex(COLUMN_EX_OPTID_SET));

            results.add(new ExclusionModel(selFacility_id,selOptions_id,setFacility_id,setOptions_id));
        }
        db.close();
        cursor.close();
        return results;
    }

    public boolean AddExclusionsData(ArrayList<ExclusionModel> arrayList) {

        System.out.println("adding" + arrayList.size());
        long result = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println("AddExclusionsData: " + db);
        db.beginTransaction();
        try {

            db.delete(TABLE_EXCLUSION, null, null);
            ContentValues values = new ContentValues();
            for (int i = 0; i < arrayList.size(); i++) {
                values.put(COLUMN_EX_FID_SELECT, arrayList.get(i).getSelFacility_id());
                values.put(COLUMN_EX_OPTID_SELECT, arrayList.get(i).getSelOptions_id());
                values.put(COLUMN_EX_FID_SET, arrayList.get(i).getSetFacility_id());
                values.put(COLUMN_EX_OPTID_SET, arrayList.get(i).getSetOptions_id());

                result = db.insert(TABLE_EXCLUSION, null, values);
            }
            if (result == -1) {
                return false;
            } else {
                db.setTransactionSuccessful();
            }
        } finally {
            db.endTransaction();
            db.close();
        }
        return true;
    }

    public boolean AddFacilityData(ArrayList<FacilityModel2> arrayList) {


        long result = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(TABLE_FACILITIES, null, null);
            ContentValues values = new ContentValues();
            for (int i = 0; i < arrayList.size(); i++) {
                values.put(COLUMN_FID, arrayList.get(i).getFacility_id());
                values.put(COLUMN_FNAME, arrayList.get(i).getName());

                for (int j = 0; j < arrayList.get(i).getOptions().size(); j++) {
                    values.put(COLUMN_OPT_ID, arrayList.get(i).getOptions().get(j).getId());
                    values.put(COLUMN_OPT_NAME, arrayList.get(i).getOptions().get(j).getName());
                    values.put(COLUMN_OPT_ICON, arrayList.get(i).getOptions().get(j).getIcon());

                    result = db.insert(TABLE_FACILITIES, null, values);
                }
            }
            if (result == -1) {
                return false;
            } else {
                db.setTransactionSuccessful();
            }
        } finally {
            db.endTransaction();
            db.close();
        }
        return true;
    }

}

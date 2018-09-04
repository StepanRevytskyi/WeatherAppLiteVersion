package com.example.arondillqs5328.weatherappliteversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.arondillqs5328.weatherappliteversion.R;

public class CityDatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "weather";

    public CityDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE cities (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "CITY TEXT, "
            + "WEATHER TEXT, "
            + "MIN_TEMPERATURE INTEGER, "
            + "MAX_TEMPERATURE INTEGER, "
            + "WEATHER_ICON INTEGER); ");

        insertDefaultCity(db, "default", "unknown", 0, 0, R.drawable.unknown);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //locate by default
    private static void insertDefaultCity(@NonNull SQLiteDatabase db, String city, String weather,
                                          int minTemp, int maxTemp, int icon) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("CITY", city);
        contentValues.put("WEATHER", weather);
        contentValues.put("MIN_TEMPERATURE", minTemp);
        contentValues.put("MAX_TEMPERATURE", maxTemp);
        contentValues.put("WEATHER_ICON", icon);

        db.insert("cities", null, contentValues);
    }
}

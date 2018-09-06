package com.example.arondillqs5328.weatherappliteversion.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CityDatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "weather";

    public CityDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE cities (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "uuid TEXT, "
            + "city TEXT, "
            + "weather TEXT, "
            + "min_temperature INTEGER, "
            + "max_temperature INTEGER, "
            + "weather_icon INTEGER); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

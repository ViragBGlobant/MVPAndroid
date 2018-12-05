package com.myapplication.databases;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;

public class StringConverter {

    @TypeConverter
    public static String[] fromString(String screenshotsJson) {
        if (screenshotsJson == null) return null;

        Gson gson = new Gson();
        return gson.fromJson(screenshotsJson, String[].class);
    }

    @TypeConverter
    public static String toString(String[] screenshots) {
        if (screenshots == null) return null;

        Gson gson = new Gson();
        return gson.toJson(screenshots);
    }
}

package com.myapplication.databases;

import android.arch.persistence.room.Room;
import android.content.Context;

public class AppDatabaseClient {

    private static final String DATABASE_NAME = "NATION_DATABASE";

    private static AppDatabaseClient mInstance;
    private final AppDatabase appDatabase;

    private AppDatabaseClient(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
    }

    public static synchronized AppDatabaseClient getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new AppDatabaseClient(context);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
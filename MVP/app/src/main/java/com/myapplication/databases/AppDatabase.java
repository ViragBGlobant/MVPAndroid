package com.myapplication.databases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.myapplication.models.Nation;

@Database(entities = {Nation.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NationDAO nationDAO();
}

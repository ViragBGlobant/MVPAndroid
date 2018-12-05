package com.myapplication.databases;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.myapplication.models.Nation;

import java.util.List;

@Dao
public interface NationDAO {

    @Query("SELECT * FROM NATION")
    List<Nation> getAllNations();

    @Insert
    void insert(Nation nation);

    @Delete
    void delete(Nation nation);

    @Update
    void update(Nation nation);
}

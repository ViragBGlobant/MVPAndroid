package com.myapplication.api.nation;

import com.myapplication.models.Nation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NationDataService {

    @GET("all/")
    Call<List<Nation>> getNations();
}

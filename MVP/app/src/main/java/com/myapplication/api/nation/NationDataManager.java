package com.myapplication.api.nation;

import com.myapplication.application.KYCApplication;
import com.myapplication.interactors.ListingsInteractor;
import com.myapplication.models.Nation;
import com.myapplication.networks.retro.RetrofitClient;
import com.myapplication.networks.utils.NetworkStatusUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NationDataManager {

    private static NationDataManager instance;

    private NationDataManager(){}

    public static NationDataManager getInstance() {
        if(instance == null){
            instance = new NationDataManager();
        }
        return instance;
    }

    public List<Nation> getNationsData(final ListingsInteractor.onListingDownloadFinishedListener listener) {
        if (NetworkStatusUtils.isInternetAvailable(KYCApplication.getAppContext())) {
            NationDataService service = RetrofitClient.getRetrofitClient().create(NationDataService.class);
            Call<List<Nation>> call = service.getNations();
            call.enqueue(new Callback<List<Nation>>() {
                @Override
                public void onResponse(Call<List<Nation>> call, Response<List<Nation>> response) {
                    if (response.body() != null) {
                        if (!response.body().isEmpty()) {
                            // TODO: Save to the database.
                            listener.onListingDownloadSuccess(response.body());
                        } else {
                            listener.onEmptyListingReceived();
                        }
                    } else {
                        listener.onListingDownloadFailure();
                    }
                }

                @Override
                public void onFailure(Call<List<Nation>> call, Throwable t) {
                    listener.onListingDownloadFailure();
                }
            });
        } else {
            // TODO: Fetch data from database.
        }
        return null;
    }
}

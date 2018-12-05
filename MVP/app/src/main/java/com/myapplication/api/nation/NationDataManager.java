package com.myapplication.api.nation;

import com.myapplication.application.KYCApplication;
import com.myapplication.databases.AppDatabaseClient;
import com.myapplication.interactors.ListingsInteractor;
import com.myapplication.models.Nation;
import com.myapplication.networks.retro.RetrofitClient;
import com.myapplication.networks.utils.NetworkStatusUtils;
import com.myapplication.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NationDataManager {

    private static NationDataManager instance;

    private NationDataManager(){}

    public static NationDataManager getInstance() {
        if (instance == null) {
            instance = new NationDataManager();
        }
        return instance;
    }

    public void getNationsData(final ListingsInteractor.onListingDownloadFinishedListener listener) {
        if (NetworkStatusUtils.isInternetAvailable(KYCApplication.getAppContext())) {
            NationDataService service = RetrofitClient.getRetrofitClient().create(NationDataService.class);
            Call<List<Nation>> call = service.getNations();
            call.enqueue(new Callback<List<Nation>>() {
                @Override
                public void onResponse(Call<List<Nation>> call, Response<List<Nation>> response) {
                    List<Nation> nationList = getOnlineNationsData(response);
                    if (Utils.isNotNull(nationList) && !nationList.isEmpty()) {
                        insertNationIntoDatabase(nationList);
                        listener.onListingDownloadSuccess(response.body());
                    } else {
                        listener.onEmptyListingReceived();
                    }
                }

                @Override
                public void onFailure(Call<List<Nation>> call, Throwable t) {
                    listener.onListingDownloadFailure();
                }
            });
        } else {
            List<Nation> nationList = getNationsFromDatabase();
            if (Utils.isNotNull(nationList)) {
                if (!nationList.isEmpty()) {
                    listener.onListingDownloadSuccess(nationList);
                } else {
                    listener.onEmptyListingReceived();
                }
            } else {
                listener.onListingDownloadFailure();
            }
        }
    }

    private List<Nation> getOnlineNationsData(Response<List<Nation>> response) {
        if (Utils.isNotNull(response) && Utils.isNotNull(response.body())) {
            return response.body();
        }
        return null;
    }

    private void insertNationIntoDatabase(List<Nation> nationList) {
        if (getNationsFromDatabase().isEmpty()) {
            for (Nation nation : nationList) {
                AppDatabaseClient.
                        getInstance(KYCApplication.getAppContext()).getAppDatabase()
                        .nationDAO()
                        .insert(nation);
            }
        }
    }

    private List<Nation> getNationsFromDatabase() {
        return AppDatabaseClient
                .getInstance(KYCApplication.getAppContext())
                .getAppDatabase()
                .nationDAO()
                .getAllNations();
    }
}

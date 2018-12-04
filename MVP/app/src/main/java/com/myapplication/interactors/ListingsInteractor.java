package com.myapplication.interactors;

import com.myapplication.api.nation.NationDataManager;
import com.myapplication.models.Nation;
import java.util.List;

public class ListingsInteractor {

    private static final String TAG = ListingsInteractor.class.getSimpleName();

    public interface onListingDownloadFinishedListener {

        void onListingDownloadSuccess(List<Nation> nations);

        void onListingDownloadFailure();

        void onEmptyListingReceived();
    }

    public void downloadListings(final onListingDownloadFinishedListener listener) {
        NationDataManager nationDataManager = NationDataManager.getInstance();
        nationDataManager.getNationsData(listener);
    }
}

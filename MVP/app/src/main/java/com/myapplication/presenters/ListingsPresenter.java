package com.myapplication.presenters;

import com.myapplication.interactors.ListingsInteractor;
import com.myapplication.models.Nation;
import com.myapplication.utils.Utils;
import com.myapplication.views.ListingsView;

import java.util.List;

public class ListingsPresenter implements ListingsInteractor.onListingDownloadFinishedListener {

    private static final String TAG = ListingsPresenter.class.getSimpleName();

    private final ListingsView listingsView;
    private final ListingsInteractor listingsInteractor;

    public ListingsPresenter(ListingsView listingsView, ListingsInteractor listingsInteractor) {
        this.listingsView = listingsView;
        this.listingsInteractor = listingsInteractor;
    }

    public void startListingsDownload() {
        if (Utils.isNotNull(listingsView)) {
            listingsView.startLoadingNations();
        }

        listingsInteractor.downloadListings(this);
    }

    @Override
    public void onListingDownloadSuccess(List<Nation> nations) {
        if (Utils.isNotNull(listingsView)) {
            listingsView.onSuccess(nations);
        }
    }

    @Override
    public void onListingDownloadFailure() {
        if (Utils.isNotNull(listingsView)) {
            listingsView.onFailure();
        }
    }

    @Override
    public void onEmptyListingReceived() {
        if (Utils.isNotNull(listingsView)) {
            listingsView.onEmptyListReceived();
        }
    }
}

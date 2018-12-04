package com.myapplication.presenters;

import com.myapplication.interactors.ListingsInteractor;
import com.myapplication.models.Nation;
import com.myapplication.views.ListingsView;

import java.util.List;

public class ListingsPresenter implements ListingsInteractor.onListingDownloadFinishedListener {

    private static final String TAG = ListingsPresenter.class.getSimpleName();

    private ListingsView listingsView;
    private ListingsInteractor listingsInteractor;

    public ListingsPresenter(ListingsView listingsView, ListingsInteractor listingsInteractor) {
        this.listingsView = listingsView;
        this.listingsInteractor = listingsInteractor;
    }

    public void startListingsDownload() {
        if (listingsView != null) {
            listingsView.startLoadingNations();
        }

        listingsInteractor.downloadListings(this);
    }

    @Override
    public void onListingDownloadSuccess(List<Nation> nations) {
        if (listingsView != null) {
            listingsView.onSuccess(nations);
        }
    }

    @Override
    public void onListingDownloadFailure() {
        if (listingsView != null) {
            listingsView.onFailure();
        }
    }

    @Override
    public void onEmptyListingReceived() {
        if (listingsView != null) {
            listingsView.onEmptyListReceived();
        }
    }
}

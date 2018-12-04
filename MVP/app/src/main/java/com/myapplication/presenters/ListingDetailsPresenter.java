package com.myapplication.presenters;

import android.content.Context;

import com.myapplication.models.Nation;
import com.myapplication.views.ListingDetailsView;

public class ListingDetailsPresenter {

    private ListingDetailsView listingDetailsView;

    public ListingDetailsPresenter(ListingDetailsView listingDetailsView) {
        this.listingDetailsView = listingDetailsView;
    }

    public void showListingDetails(Nation nation) {
        if (nation != null) {
            listingDetailsView.loadListingDetails(nation);
        } else {
            listingDetailsView.errorLoadingListingDetails();
        }
    }


    public void onMapButtonClicked(String latitude, String longitude) {
        listingDetailsView.onMapButtonClicked(latitude, longitude);
    }
}

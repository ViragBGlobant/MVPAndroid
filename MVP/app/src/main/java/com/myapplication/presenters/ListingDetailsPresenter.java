package com.myapplication.presenters;

import com.myapplication.models.Nation;
import com.myapplication.utils.Utils;
import com.myapplication.views.ListingDetailsView;

public class ListingDetailsPresenter {

    private final ListingDetailsView listingDetailsView;

    public ListingDetailsPresenter(ListingDetailsView listingDetailsView) {
        this.listingDetailsView = listingDetailsView;
    }

    public void showListingDetails(Nation nation) {
        if (Utils.isNotNull(nation)) {
            listingDetailsView.loadListingDetails(nation);
        } else {
            listingDetailsView.errorLoadingListingDetails();
        }
    }


    public void onMapButtonClicked(String latitude, String longitude) {
        listingDetailsView.onMapButtonClicked(latitude, longitude);
    }
}

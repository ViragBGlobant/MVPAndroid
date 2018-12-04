package com.myapplication.views;

import com.myapplication.models.Nation;

public interface ListingDetailsView {

    void loadListingDetails(Nation nation);

    void errorLoadingListingDetails();

    void onMapButtonClicked(String latitude, String longitude);
}

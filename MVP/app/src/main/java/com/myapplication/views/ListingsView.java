package com.myapplication.views;

import com.myapplication.models.Nation;

import java.util.List;

public interface ListingsView {

    void startLoadingNations();

    void onSuccess(List<Nation> nations);

    void onFailure();

    void onEmptyListReceived();
}

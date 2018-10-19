package com.myapplication.interactors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.myapplication.models.Nation;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ListingsInteractor {

    private static final String URL = "https://restcountries.eu/rest/v2/all";

    public interface onListingDownloadFinishedListener {

        void onListingDownloadSuccess(List<Nation> nations);

        void onListingDownloadFailure();
    }

    public void downloadListings(final onListingDownloadFinishedListener listener) {
        // TODO: Take this task to APIManager class
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (responseBody != null) {
                    String response = new String(responseBody, StandardCharsets.UTF_8);
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Nation>>() {}.getType();
                    List<Nation> nations = gson.fromJson(response, type);
                    listener.onListingDownloadSuccess(nations);
                } else {
                    listener.onListingDownloadFailure();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                listener.onListingDownloadFailure();
            }
        });
    }
}

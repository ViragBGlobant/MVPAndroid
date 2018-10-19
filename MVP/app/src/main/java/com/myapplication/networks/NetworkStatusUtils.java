package com.myapplication.networks;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkStatusUtils {

    public static final String TAG = NetworkStatusUtils.class.getSimpleName();

    public static boolean isInternetAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
        } else {
            return false;
        }
    }
}

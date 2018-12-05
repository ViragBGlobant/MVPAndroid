package com.myapplication.networks.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.myapplication.utils.Utils;

public class NetworkStatusUtils {

    public static final String TAG = NetworkStatusUtils.class.getSimpleName();

    public static boolean isInternetAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        if (Utils.isNotNull(connectivityManager)) {
            return Utils.isNotNull(connectivityManager.getActiveNetworkInfo()) && connectivityManager.getActiveNetworkInfo().isConnected();
        } else {
            return false;
        }
    }
}

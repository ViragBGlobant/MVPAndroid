package com.myapplication.utils;

import android.util.Log;

public class LogUtils {

    public static final String TAG = LogUtils.class.getSimpleName();

    public static void printLog(String tag, String message) {
        Log.d(tag, message);
    }
}

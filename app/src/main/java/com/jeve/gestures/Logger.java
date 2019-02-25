package com.jeve.gestures;

import android.util.Log;

/**
 * 日志类
 * 2018-3-28
 */

public class Logger {

    private static Boolean log = true;

    private static final String TAG = "LJW";

    public static void setLog(Boolean log) {
        Logger.log = log;
    }

    public static void d(String content) {
        if (log)
            Log.e(TAG, content);
    }

}

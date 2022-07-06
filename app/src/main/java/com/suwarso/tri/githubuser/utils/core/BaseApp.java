package com.suwarso.tri.githubuser.utils.core;

import android.app.Application;

import com.suwarso.tri.githubuser.utils.ConnectivityReceiver;

public class BaseApp extends Application {

    private static BaseApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized BaseApp getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}


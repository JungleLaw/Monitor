package com.law.demo;

import android.app.Application;

import cn.law.android.monitor.Monitor;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Monitor.install(this);
    }
}

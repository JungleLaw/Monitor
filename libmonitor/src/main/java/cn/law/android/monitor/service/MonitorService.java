package cn.law.android.monitor.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MonitorService extends Service {
    public static final String KEY = "k";
    public static final String SHOW = "show";
    public static final String HIDE = "hide";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println(intent.getStringExtra(KEY));
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

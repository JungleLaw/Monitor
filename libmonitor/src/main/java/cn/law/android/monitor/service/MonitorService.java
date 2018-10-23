package cn.law.android.monitor.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import cn.law.android.monitor.R;
import cn.law.android.monitor.view.Console;

public class MonitorService extends Service {
    public static final String KEY = "k";
    public static final String SHOW = "show";
    public static final String HIDE = "hide";

    private Console mConsole;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println(intent.getStringExtra(KEY));
        if (intent.getStringExtra(KEY).equals(SHOW)) {
            show();
        } else {
            hide();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void show() {
        if (mConsole == null) {
            mConsole = new Console(this);
            mConsole.show();
        }
    }

    private void hide() {
        if (mConsole != null) {
            mConsole.hide();
            mConsole = null;
            stopSelf();
        }
    }
}

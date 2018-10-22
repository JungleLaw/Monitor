package cn.law.android.monitor.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import cn.law.android.monitor.R;

public class MonitorService extends Service {
    public static final String KEY = "k";
    public static final String SHOW = "show";
    public static final String HIDE = "hide";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println(intent.getStringExtra(KEY));
        show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void show() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.type = WindowManager.LayoutParams.TYPE_PHONE;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        params.width = 200;
        params.height = 200;
        params.gravity = Gravity.START | Gravity.TOP;
        View view = View.inflate(this, R.layout.layout_test, null);
        wm.addView(view, params);
    }

    private void hide() {

    }
}

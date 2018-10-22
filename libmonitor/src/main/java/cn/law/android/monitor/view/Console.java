package cn.law.android.monitor.view;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import cn.law.android.monitor.R;

public class Console extends LinearLayout {
    /**
     * Overlay Type
     */
    private static final int OVERLAY_TYPE;

    static {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N_MR1) {
            OVERLAY_TYPE = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE;
        } else {
            OVERLAY_TYPE = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }
    }

    public Console(Context context) {
        super(context);
        initialize();
    }

    private void initialize() {
        inflate(getContext(), R.layout.layout_console, this);

//        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        mParams = new WindowManager.LayoutParams();
//        mParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
//        mParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//        mParams.type = OVERLAY_TYPE;
//        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |
//                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
//        mParams.format = PixelFormat.TRANSLUCENT;
//        // 左下の座標を0とする
//        mParams.gravity = Gravity.LEFT | Gravity.BOTTOM;
    }

    public void show() {

    }

    public void hide() {

    }
}

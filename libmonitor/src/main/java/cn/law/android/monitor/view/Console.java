package cn.law.android.monitor.view;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import cn.law.android.monitor.R;
import cn.law.android.monitor.event.TrackerEvent;
import cn.law.android.monitor.view.adapter.LogAdapter;
import cn.law.android.monitor.view.entity.Logger;

public class Console extends LinearLayout {
    private WindowManager wm;
    private WindowManager.LayoutParams params;

    private TextView mTvTracker;
    private RecyclerView mRvLogs;

    private LogAdapter mAdapter;
    private List<Logger> mLoggers;

    public Console(Context context) {
        super(context);
        initialize();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventBus.getDefault().unregister(this);
    }

    private void initialize() {
        inflate(getContext(), R.layout.layout_console, this);
        mTvTracker = findViewById(R.id.tv_tracker);
        mRvLogs = findViewById(R.id.rv_logs);
        mRvLogs.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mLoggers = new ArrayList<>();

        // TODO: 2018/11/13
        for (int i = 0, c = 25; i < c; i++) {
            mLoggers.add(new Logger());
        }

        mAdapter = new LogAdapter(getContext(), mLoggers);
        mRvLogs.setAdapter(mAdapter);
        mRvLogs.scrollToPosition(mAdapter.getItemCount() - 1);

        wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        params = new WindowManager.LayoutParams();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            params.type = WindowManager.LayoutParams.TYPE_PHONE;
        } else {
            params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_FULLSCREEN;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.START | Gravity.TOP;
        params.format = PixelFormat.TRANSLUCENT;
    }

    public void show() {
        wm.addView(this, params);
    }

    public void hide() {
        wm.removeViewImmediate(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onActivityChange(TrackerEvent event) {
        mTvTracker.setText(event.activity);
    }
}

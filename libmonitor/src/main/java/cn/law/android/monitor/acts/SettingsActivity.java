package cn.law.android.monitor.acts;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import cn.law.android.monitor.Constants;
import cn.law.android.monitor.Monitor;
import cn.law.android.monitor.R;
import cn.law.android.monitor.Switch;
import cn.law.android.monitor.base.BaseActivity;
import cn.law.android.monitor.service.MonitorService;
import cn.law.android.monitor.widget.Option;

public class SettingsActivity extends BaseActivity {
    private static final int OVERLAY_PERMISSION_REQ_CODE = 100;

    private Option mDebuggingSwt;
    private Option mLogSwt;
    private Option mTrackerSwt;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void initVariables() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_settings;
    }

    @Override
    protected void initView() {
        mDebuggingSwt = findViewById(R.id.swt_debugging);
        mLogSwt = findViewById(R.id.swt_log);
        mTrackerSwt = findViewById(R.id.swt_tracker);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        boolean debugging = mSharedPreferences.getBoolean(Constants.PrefsKey.DEBUGGING, false);
        mDebuggingSwt.setChecked(debugging);
        mLogSwt.setEnable(debugging);
        mTrackerSwt.setEnable(debugging);
        mLogSwt.setChecked(mSharedPreferences.getBoolean(Constants.PrefsKey.LOG, false));
        mTrackerSwt.setChecked(mSharedPreferences.getBoolean(Constants.PrefsKey.TRACKER, false));
        mDebuggingSwt.setCallback(new Option.Callback() {
            @Override
            public void onCheckedChangeCallback(boolean checked) {
                if (checked) {
                    show();
                } else {
                    hide();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        Switch.debugging = mDebuggingSwt.isChecked();
        Switch.log = mLogSwt.isChecked();
        Switch.tracker = mTrackerSwt.isChecked();
        mSharedPreferences.edit()
                .putBoolean(Constants.PrefsKey.DEBUGGING, mDebuggingSwt.isChecked())
                .putBoolean(Constants.PrefsKey.LOG, mLogSwt.isChecked())
                .putBoolean(Constants.PrefsKey.TRACKER, mTrackerSwt.isChecked())
                .apply();
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    mDebuggingSwt.setChecked(false);
                    mLogSwt.setEnable(false);
                    mTrackerSwt.setEnable(false);
                    Toast.makeText(this, "open fail", Toast.LENGTH_SHORT).show();
                } else {
                    show();
                }
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void show() {
        Intent intent = new Intent(this, MonitorService.class);
        intent.putExtra(MonitorService.KEY, MonitorService.SHOW);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            ContextCompat.startForegroundService(this, intent);
            mLogSwt.setEnable(true);
            mTrackerSwt.setEnable(true);
            return;
        }

        if (Settings.canDrawOverlays(this)) {
            ContextCompat.startForegroundService(this, intent);
            mLogSwt.setEnable(true);
            mTrackerSwt.setEnable(true);
            return;
        }

        intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
    }

    private void hide() {
        Intent intent = new Intent(this, MonitorService.class);
        intent.putExtra(MonitorService.KEY, MonitorService.HIDE);
        ContextCompat.startForegroundService(this, intent);
        mLogSwt.setEnable(false);
        mTrackerSwt.setEnable(false);
    }

}

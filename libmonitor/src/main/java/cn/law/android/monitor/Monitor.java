package cn.law.android.monitor;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import cn.law.android.monitor.acts.SettingsActivity;
import cn.law.android.monitor.event.TrackerEvent;

public class Monitor {
    private static final String TAG = Monitor.class.getName();

//    private static ActivityInfo activityInfo;

    public static void install(Application app) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(app.getApplicationContext());
//        Switch.debugging = sharedPreferences.getBoolean(Constants.PrefsKey.DEBUGGING, false);
        Switch.log = sharedPreferences.getBoolean(Constants.PrefsKey.LOG, false);
        Switch.tracker = sharedPreferences.getBoolean(Constants.PrefsKey.TRACKER, false);
        app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
//                activityInfo = new ActivityInfo(activity.getPackageName(), activity.getLocalClassName());
                Log.d(TAG, "Switch.debugging = " + Switch.debugging + ",Switch.tracker = " + Switch.tracker);
                if (Switch.debugging && Switch.tracker) {
                    Log.d(TAG, activity.getPackageName() + "." + activity.getLocalClassName());
                    EventBus.getDefault().post(new TrackerEvent(activity.getPackageName() + "." + activity.getLocalClassName()));
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (Switch.debugging && Switch.tracker) {
                    Log.d(TAG, activity.getPackageName() + "." + activity.getLocalClassName());
                    EventBus.getDefault().post(new TrackerEvent(activity.getPackageName() + "." + activity.getLocalClassName()));
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {
                if (Switch.debugging && Switch.tracker) {
                    Log.d(TAG, activity.getPackageName() + "." + activity.getLocalClassName());
                    EventBus.getDefault().post(new TrackerEvent(activity.getPackageName() + "." + activity.getLocalClassName()));
                }
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    public static void openSettings(Context context) {
        context.startActivity(new Intent(context, SettingsActivity.class));
    }

    public static class ActivityInfo {
        public String packageName;
        public String compenentName;

        public ActivityInfo(String packageName, String compenentName) {
            this.packageName = packageName;
            this.compenentName = compenentName;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getCompenentName() {
            return compenentName;
        }

        public void setCompenentName(String compenentName) {
            this.compenentName = compenentName;
        }
    }
}

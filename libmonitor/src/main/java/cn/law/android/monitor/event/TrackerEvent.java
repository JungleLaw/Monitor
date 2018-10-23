package cn.law.android.monitor.event;

public class TrackerEvent extends Event {
    public String activity;

    public TrackerEvent(String activity) {
        this.activity = activity;
    }
}

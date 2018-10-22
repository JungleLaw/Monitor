package cn.law.android.monitor;

public class JNI {
    static {
        System.loadLibrary("monitor");
    }

    public static native String test();
}

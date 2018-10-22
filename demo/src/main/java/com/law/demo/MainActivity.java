package com.law.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import cn.law.android.monitor.JNI;
import cn.law.android.monitor.Monitor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.tv);
        tv.setText(JNI.test());
    }

    public void openSettings(View view) {
        Monitor.openSettings(this);
//        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
    }
}

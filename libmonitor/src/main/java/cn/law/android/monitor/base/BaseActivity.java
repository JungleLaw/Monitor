package cn.law.android.monitor.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
        if (getLayoutID() > 0) {
            setContentView(getLayoutID());
        } else {
            throw new IllegalArgumentException("layout id error!");
        }
        initView();
        initListener();
        initData();
    }

    protected abstract void initVariables();

    protected abstract int getLayoutID();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();
}

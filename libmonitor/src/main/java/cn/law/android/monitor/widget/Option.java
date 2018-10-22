package cn.law.android.monitor.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import cn.law.android.monitor.R;

public class Option extends LinearLayout {
    private TextView mTvName;
    private Switch mSwitch;

    private String name;
    private boolean enable;

    private Callback mCallback;

    public Option(Context context) {
        this(context, null, 0);
    }

    public Option(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Option(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(attrs);
    }

    private void initialize(@Nullable AttributeSet attrs) {
        initAttrs(attrs);
        initView();
    }

    private void initAttrs(@Nullable AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.Option, 0, 0);
        name = ta.getString(R.styleable.Option_name);
        if (TextUtils.isEmpty(name)) {
            name = "option name";
        }
        ta.recycle();//不要忘记回收！
    }

    private void initView() {
        inflate(getContext(), R.layout.layout_option, this);
        mTvName = findViewById(R.id.tv_name);
        mSwitch = findViewById(R.id.swt);
        mTvName.setText(name);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (mCallback != null) {
                    mCallback.onCheckedChangeCallback(checked);
                }
            }
        });
    }

    public void setCallback(Callback cb) {
        this.mCallback = cb;
    }

    public interface Callback {
        void onCheckedChangeCallback(boolean checked);
    }

    public boolean isChecked() {
        return mSwitch.isChecked();
    }

    public void setChecked(boolean checked) {
        mSwitch.setChecked(checked);
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
        mSwitch.setEnabled(enable);
    }
}

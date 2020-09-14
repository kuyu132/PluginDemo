package com.kuyu.plugintest;

import android.os.Bundle;
import com.kuyu.pluginlib.BaseActivity;

/**
 * 用于生成插件的apk被加载
 */
public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

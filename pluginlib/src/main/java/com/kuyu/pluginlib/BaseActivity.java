package com.kuyu.pluginlib;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * created by wangguoqun at 2020-09-13
 * 通过类加载器加载出来的activity和正常的activity比，缺少：1.生命周期 2.上下文
 */
public class BaseActivity extends Activity implements ActivityInterface {
    //上下文，代理类
    protected Activity that;

    @Override
    public void attach(Activity activity) {
        that = activity;
    }

    @Override public void setContentView(View view) {
        if (that == null) {
            super.setContentView(view);
        } else {
            that.setContentView(view);
        }
    }

    @Override public void setContentView(int layoutResID) {
        that.setContentView(layoutResID);
    }

    @Override public <T extends View> T findViewById(int id) {
        return that.findViewById(id);
    }

    @Override public Intent getIntent() {
        return that.getIntent();
    }

    @Override public ClassLoader getClassLoader() {
        return that.getClassLoader();
    }

    @NonNull @Override public LayoutInflater getLayoutInflater() {
        return that.getLayoutInflater();
    }

    @Override public void startActivity(Intent intent) {
        Intent real = new Intent();
        real.putExtra("className",intent.getComponent().getClassName());
        that.startActivity(real);
    }

    @Override public Resources getResources() {
        return that.getResources();
    }

    @Override public ApplicationInfo getApplicationInfo() {
        return that.getApplicationInfo();
    }

    @Override public Window getWindow() {
        return that.getWindow();
    }

    @Override public WindowManager getWindowManager() {
        return that.getWindowManager();
    }

    @SuppressLint("MissingSuperCall")
    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    }

    @SuppressLint("MissingSuperCall")
    @Override public void onStart() {
    }

    @SuppressLint("MissingSuperCall")
    @Override public void onResume() {
    }

    @SuppressLint("MissingSuperCall")
    @Override public void onPause() {
    }

    @SuppressLint("MissingSuperCall")
    @Override public void onStop() {
    }

    @SuppressLint("MissingSuperCall")
    @Override public void onDestroy() {
    }

    @Override public void onSaveInstanceState(@NonNull Bundle outState) {

    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override public void onBackPressed() {
    }

}

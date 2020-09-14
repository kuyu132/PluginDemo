package com.kuyu.pluginlib;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import androidx.annotation.NonNull;

/**
 * created by wangguoqun at 2020-09-13
 */
public interface ActivityInterface {
    void attach(Activity activity);

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onSaveInstanceState(@NonNull Bundle outState);

    boolean onTouchEvent(MotionEvent event);

    void onBackPressed();
}

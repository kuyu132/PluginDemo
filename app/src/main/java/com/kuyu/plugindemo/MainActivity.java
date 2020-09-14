package com.kuyu.plugindemo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.kuyu.pluginlib.PluginManager;
import com.kuyu.pluginlib.ProxyActivity;

/**
 * 壳apk，主要是加载插件activity
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_hello).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                jumpActivity();
            }
        });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    protected void jumpActivity() {
        PluginManager.getInstance().setContext(getApplicationContext());
        PluginManager.getInstance()
                .loadPlugin(Environment.getExternalStorageDirectory() + "/tmp.apk");
        PackageInfo packageInfo = PluginManager.getInstance().getPackageInfo();
        if (packageInfo == null || packageInfo.activities == null || packageInfo.activities.length == 0) {
            return;
        }
        Intent intent = new Intent(this, ProxyActivity.class);
        intent.putExtra("className", packageInfo.activities[0].name);
        startActivity(intent);
    }

    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        if(requestCode == 1){
            Log.i("kuyu","收到授权结果了");
        }
    }
}

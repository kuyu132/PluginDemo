package com.kuyu.pluginlib;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.Nullable;
import dalvik.system.DexClassLoader;

/**
 * created by wangguoqun at 2020-09-13
 */
public class ProxyActivity extends Activity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent != null) {
            String className = intent.getStringExtra("className");
            if (className != null && className.length() > 0) {
                DexClassLoader dexClassLoader = PluginManager.getInstance().getDexClassLoader();
                try {
                    Class<?>  clz = dexClassLoader.loadClass(className);
                    //将activity实例化
                    Object pluginActivity = clz.newInstance();
                    if(pluginActivity instanceof ActivityInterface){
                        ActivityInterface activityInterface = ((ActivityInterface) pluginActivity);
                        //使用子类
                        activityInterface.attach(this);
                        activityInterface.onCreate(savedInstanceState);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override public Resources getResources() {
        return PluginManager.getInstance().getPluginResources();
    }

    @Override public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }
}

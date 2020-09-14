package com.kuyu.pluginlib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.lang.reflect.Method;

/**
 * created by wangguoqun at 2020-09-13
 * 用于加载插件
 */
public class PluginManager {

    private static final PluginManager instance = new PluginManager();
    private Context mContext;
    private Resources mPluginResources;
    private DexClassLoader mDexClassLoader;
    private PackageInfo mPackageInfo;

    private PluginManager() {
    }

    public static PluginManager getInstance() {
        return instance;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public void loadPlugin(String pluginPath) {
        //包管理器
        PackageManager packageManager = mContext.getPackageManager();
        //包信息类
        mPackageInfo =
                packageManager.getPackageArchiveInfo(pluginPath, PackageManager.GET_ACTIVITIES);
                //packageManager.getPackageArchiveInfo(pluginPath, 0);
        //获取类加载器
        File pluginFile = mContext.getDir("plugin", Context.MODE_PRIVATE);
        mDexClassLoader = new DexClassLoader(pluginPath, pluginFile.getAbsolutePath(), null,
                mContext.getClassLoader());
        //获取插件的resources，通过context获取到的是当前的
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath =
                    assetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, pluginPath);
            mPluginResources =
                    new Resources(assetManager, mContext.getResources().getDisplayMetrics(),
                            mContext.getResources().getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Resources getPluginResources() {
        return mPluginResources;
    }

    public DexClassLoader getDexClassLoader() {
        return mDexClassLoader;
    }

    public PackageInfo getPackageInfo() {
        return mPackageInfo;
    }
}

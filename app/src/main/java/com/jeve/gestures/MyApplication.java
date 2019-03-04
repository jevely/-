package com.jeve.gestures;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.jeve.gestures.content.ContentManager;

import java.util.List;

import cn.bmob.v3.Bmob;
import keeplive.je.com.keeplivesupportlibrary.KeepLiveManager;

public class MyApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        //只初始化一次 限主进程
        String processName = getProcessName(this, android.os.Process.myPid());
        if (null != processName) {
            if (!processName.equals(getPackageName())) {
                return;
            }
        }

        //初始化数据库
        ContentManager.getInstance().initDB();
        //初始化保活
        KeepLiveManager.INSTANCE.init(this);

        Bmob.initialize(this, "dbedc389b5b8c55f6d67074a3dacf111");
    }

    public static Context getContext() {
        return context;
    }

    /**
     * 获取app进程
     */
    public String getProcessName(Context context, int pid) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }
}

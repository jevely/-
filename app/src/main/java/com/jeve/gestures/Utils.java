package com.jeve.gestures;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AppOpsManager;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.view.KeyEvent;

import java.lang.reflect.Method;

public class Utils {

    /**
     * 检查悬浮窗权限
     */
    public static boolean checkFloatWindowPermission(Context context, int op) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 19) {
            AppOpsManager manager = (AppOpsManager) context.getSystemService(Context
                    .APP_OPS_SERVICE);
            try {
                Class clazz = AppOpsManager.class;
                Method method = clazz.getDeclaredMethod("checkOp", int.class, int.class, String
                        .class);
                int result = (int) method.invoke(manager, op, Binder.getCallingUid(), context
                        .getPackageName());
                return AppOpsManager.MODE_ALLOWED == result || result == AppOpsManager.MODE_DEFAULT;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return true;
        }
    }

    /**
     * 开启悬浮窗权限（messenger）
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static void openFloatWindowPermission(Activity activity) {
        try {
            Intent intent;
            if (android.os.Build.MANUFACTURER.equals("Meizu")) {
                //魅族手机
                intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
            } else {
                intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.setData(Uri.parse("package:" + activity.getPackageName()));
            }
            activity.startActivityForResult(intent, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将dp转换成px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将像素转换成dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * back键模拟
     */
    public static void clickBack() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Instrumentation instrumentation = new Instrumentation();
                    instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.d("back点击报错: " + e.toString());
                }
            }
        }).start();

    }

}

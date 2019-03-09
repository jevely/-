package com.jeve.gestures.window;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.jeve.gestures.tool.Logger;
import com.jeve.gestures.R;
import com.jeve.gestures.tool.Utils;
import com.jeve.gestures.tool.ActionCheckTool;

/**
 * app锁屏（window）
 */
public class ActionFloatButtonWindow {

    private static ActionFloatButtonWindow lockAppWindow;

    //窗口管理器
    private WindowManager wm;
    //view
    private View mView;
    //布局参数
    private WindowManager.LayoutParams layoutParams;

    private Context context;

    private ActionFloatButtonWindow() {

    }

    public static ActionFloatButtonWindow getInstence() {
        if (lockAppWindow == null) {
            synchronized (ActionFloatButtonWindow.class) {
                if (lockAppWindow == null) {
                    lockAppWindow = new ActionFloatButtonWindow();
                }
            }
        }
        return lockAppWindow;
    }

    public static void destroy() {
        lockAppWindow = null;
    }

    public void initView(final Context context) {
        if (wm != null) return;
        this.context = context.getApplicationContext();
        Logger.d("初始化悬浮窗操作界面");
        //窗口管理器
        wm = (WindowManager) this.context.getSystemService(Context.WINDOW_SERVICE);
        //布局参数
        layoutParams = new WindowManager.LayoutParams(Utils.dip2px(context, 80),
                Utils.dip2px(context, 80));
//        layoutParams.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;//强制竖屏

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            layoutParams.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View
//                    .SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_FULLSCREEN | View
//                    .SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            layoutParams.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View
//                    .SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_FULLSCREEN;
//        }

        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        |WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        layoutParams.gravity = Gravity.START | Gravity.CENTER;//这里相当于确定起点位置
        layoutParams.width = Utils.dip2px(context, 80);
        layoutParams.height = Utils.dip2px(context, 80);

        // 设置窗体显示类型
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }

        //格式
        layoutParams.format = PixelFormat.TRANSLUCENT;
        //类型
//        layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;

        mView = View.inflate(this.context, R.layout.action_float_layout, null);

        //back键监听
        mView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    hidelockScreen();
                    return true;
                }
                return false;
            }
        });
        mView.setFocusableInTouchMode(true);

        final TextView float_tv = mView.findViewById(R.id.float_tv);
        float_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActionCheckTool.getInstance().setShouldAction()) {
                    float_tv.setText("已经开始");
                } else {
                    float_tv.setText("点击开始");
                }
            }
        });

    }

    /**
     * 展示window
     */
    public void show2() {

        if (!isShowing) {
            showlockScreen();
        }
    }

    private Boolean isShowing = false;

    /**
     * 显示锁屏
     */
    private void showlockScreen() {
        //在创建View时注册Receiver
//        IntentFilter homeFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
//        context.registerReceiver(homeListenerReceiver, homeFilter);
        isShowing = true;
        wm.addView(mView, layoutParams);
    }

    /**
     * 取消锁屏
     */
    public void hidelockScreen() {
        //在View消失时反注册Receiver
//        if (homeListenerReceiver != null && isShowing) {
//            context.unregisterReceiver(homeListenerReceiver);
//        }
        if (isShowing) wm.removeView(mView);
        isShowing = false;
    }


//    /**
//     * home、menu键监听
//     */
//    private final BroadcastReceiver homeListenerReceiver = new BroadcastReceiver() {
//        final String SYSTEM_DIALOG_REASON_KEY = "reason";
//        final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
//        final String SYSTEM_RECENT = "recentapps";
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            if (TextUtils.equals(action, Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
//                String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
//                if (reason != null && reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)) {
//                    // 处理自己的逻辑
//                    hidelockScreen();
//                } else if (reason != null && reason.equals(SYSTEM_RECENT)) {
//                    hidelockScreen();
//                }
//            }
//        }
//    };


}

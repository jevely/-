package com.jeve.gestures.service;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.jeve.gestures.tool.ActionCheckTool;
import com.jeve.gestures.tool.Logger;
import com.jeve.gestures.action.ActionManager;

/**
 * 辅助功能检测最上层应用服务
 */
public class MyAccessibilityService extends AccessibilityService {

    public static final String ACTION_BACK = "action_back";
    public static final String ACTION_POWER = "action_power";
    public static final String ACTION_RECENT = "action_recent";
    public static final String ACTION_THEME_UPDATE = "action_theme_update";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private AccessibilityNodeInfo nodeInfo;
    private String className;
    private String pakcageName;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            if (null == event.getPackageName()) {
                return;
            }

            //处理问题界面
            if (!event.getClassName().toString().contains("android.widget.FrameLayout")
                    && !event.getClassName().toString().contains("com.bytedance.sdk.openadsdk.activity.TTDelegateActivity")) {
                nodeInfo = event.getSource();
                className = event.getClassName().toString();
                pakcageName = event.getPackageName().toString();

            }

            Logger.d(pakcageName + "--" + className);

            if (!startThr2) {
                startThr2 = true;
                new Thread(new ActionThrTest()).start();
            }
        }
    }

    private boolean startThr2 = false;

//    private int time = 0;

//    private boolean iterateNodesAndHandle(AccessibilityNodeInfo nodeInfo) {
//
//        int childCount = nodeInfo.getChildCount();
//
//        //遇到ScrollView的时候模拟滑动一下
//        if (TextUtils.equals("android.widget.ScrollView", nodeInfo.getClassName())
//                || TextUtils.equals("android.widget.ListView", nodeInfo.getClassName())
//                || TextUtils.equals("android.support.v7.widget.RecyclerView", nodeInfo.getClassName())) {
//            Log.d("LJW", "开始滑动了");
//            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
//
//        }
//
//        for (int i = 0; i < childCount; i++) {
//            AccessibilityNodeInfo childNodeInfo = nodeInfo.getChild(i);
//            Log.d("LJW", "控件名字:" + childNodeInfo.getClassName());
//            if (iterateNodesAndHandle(childNodeInfo)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    private boolean clickTest(AccessibilityNodeInfo nodeInfo) {
//
//        int count = nodeInfo.getChildCount();
//
//        if (TextUtils.equals("android.widget.LinearLayout", nodeInfo.getClassName())) {
//            Log.d("LJW", "我点击了");
//            useGestureClick(nodeInfo);
//            return true;
//        }
//
//        for (int i = 0; i < count; i++) {
//            AccessibilityNodeInfo childNodeInfo = nodeInfo.getChild(i);
//            clickTest(childNodeInfo);
//            if (TextUtils.equals(childNodeInfo.getClassName(), "android.support.v7.widget.RecyclerView")) {
//                break;
//            }
//        }
//
//        return false;
//    }

//    private class ActionThr implements Runnable {
//
//        @Override
//        public void run() {
//            try {
//                while (true) {
//                    if (ActionCheckTool.getInstance().getShouldAction()) {
//                        if (time <= 120) {
//                            scrollUp(nodeInfo);
//                            Thread.sleep(2000);
//                            scrollDown(nodeInfo);
//                            Thread.sleep(2000);
//                            time += 4;
//                        } else {
//                            //退出再进来
//                            clickBack(nodeInfo);
//                            Thread.sleep(1500);
//                            clickIn(nodeInfo);
//                            Thread.sleep(3000);
//                            time = 0;
//                        }
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    private class ActionThr2 implements Runnable {
//
//        @Override
//        public void run() {
//            try {
//                while (true) {
//                    if (ActionCheckTool.getInstance().getShouldAction()) {
//                        scrollUp2(nodeInfo);
//                        Thread.sleep(3000);
//                        clickIn(nodeInfo);
////                        Thread.sleep(3000);
////                        clickIn(nodeInfo);
//                        Thread.sleep(10000);
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    private class ActionThrTest implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    Logger.d("循环睡眠开始");
                    Thread.sleep(1000);
                    Logger.d("循环睡眠完毕");
                    if (ActionCheckTool.getInstance().getShouldAction()) {
                        Logger.d("正在执行工作：" + pakcageName);
                        ActionManager.getInstance().doAction(pakcageName, className, nodeInfo, MyAccessibilityService.this);
                    } else {
//                        Logger.d("没有执行工作：" + pakcageName);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    public void scrollUp(AccessibilityNodeInfo nodeInfo) {
//        Rect rect = new Rect();
//        nodeInfo.getBoundsInScreen(rect);
//        GestureDescription.Builder builder = new GestureDescription.Builder();
//        Path path = new Path();
//        path.moveTo(rect.centerX(), rect.centerY());
//        path.lineTo(rect.centerX(), rect.centerY() - 300);
//        GestureDescription gestureDescription = builder
//                .addStroke(new GestureDescription.StrokeDescription(path, 0, 500))
//                .build();
//        dispatchGesture(gestureDescription, new AccessibilityService.GestureResultCallback() {
//            @Override
//            public void onCompleted(GestureDescription gestureDescription) {
//                super.onCompleted(gestureDescription);
//            }
//        }, null);
//    }

//    public void scrollUp2(AccessibilityNodeInfo nodeInfo) {
//        Rect rect = new Rect();
//        nodeInfo.getBoundsInScreen(rect);
//        GestureDescription.Builder builder = new GestureDescription.Builder();
//        Path path = new Path();
//        Random random = new Random();
//
//        path.moveTo(rect.centerX(), 1650);
//        path.lineTo(rect.centerX(), 350);
//
//        GestureDescription gestureDescription = builder
//                .addStroke(new GestureDescription.StrokeDescription(path, 10, 500))
//                .build();
//        dispatchGesture(gestureDescription, new AccessibilityService.GestureResultCallback() {
//            @Override
//            public void onCompleted(GestureDescription gestureDescription) {
//                super.onCompleted(gestureDescription);
//            }
//        }, null);
//    }

//    public void scrollDown(AccessibilityNodeInfo nodeInfo) {
//        Rect rect = new Rect();
//        nodeInfo.getBoundsInScreen(rect);
//        GestureDescription.Builder builder = new GestureDescription.Builder();
//        Path path = new Path();
//        path.moveTo(rect.centerX(), rect.centerY());
//        path.lineTo(rect.centerX(), rect.centerY() + 300);
//        GestureDescription gestureDescription = builder
//                .addStroke(new GestureDescription.StrokeDescription(path, 100, 500))
//                .build();
//        dispatchGesture(gestureDescription, new AccessibilityService.GestureResultCallback() {
//            @Override
//            public void onCompleted(GestureDescription gestureDescription) {
//                super.onCompleted(gestureDescription);
//            }
//        }, null);
//    }

//    public void clickBack(AccessibilityNodeInfo nodeInfo) {
//        Rect rect = new Rect();
//        nodeInfo.getBoundsInScreen(rect);
//        GestureDescription.Builder builder = new GestureDescription.Builder();
//        Path path = new Path();
//        path.moveTo(20, 100);
//        GestureDescription gestureDescription = builder
//                .addStroke(new GestureDescription.StrokeDescription(path, 100, 50))
//                .build();
//        dispatchGesture(gestureDescription, new AccessibilityService.GestureResultCallback() {
//            @Override
//            public void onCompleted(GestureDescription gestureDescription) {
//                super.onCompleted(gestureDescription);
//            }
//        }, null);
//    }

//    public void clickIn(AccessibilityNodeInfo nodeInfo) {
//        Rect rect = new Rect();
//        nodeInfo.getBoundsInScreen(rect);
//        GestureDescription.Builder builder = new GestureDescription.Builder();
//        Path path = new Path();
//        path.moveTo(rect.centerX(), rect.centerY());
//        GestureDescription gestureDescription = builder
//                .addStroke(new GestureDescription.StrokeDescription(path, 100, 50))
//                .build();
//        dispatchGesture(gestureDescription, new AccessibilityService.GestureResultCallback() {
//            @Override
//            public void onCompleted(GestureDescription gestureDescription) {
//                super.onCompleted(gestureDescription);
//            }
//        }, null);
//    }


    @Override
    public void onInterrupt() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onServiceConnected() {

    }

}

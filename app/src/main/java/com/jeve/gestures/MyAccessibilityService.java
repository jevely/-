package com.jeve.gestures;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.webkit.WebHistoryItem;

/**
 * 辅助功能检测最上层应用服务
 */
public class MyAccessibilityService extends AccessibilityService {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private AccessibilityNodeInfo nodeInfo;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            if (null == event.getPackageName()) {
                return;
            }

            nodeInfo = event.getSource();

            String packageName = event.getPackageName().toString();

            Logger.d(packageName + "----" + event.getClassName());


//            if (nodeInfo != null) {
//                iterateNodesAndHandle(nodeInfo);
//            }
//            useGestureClick(nodeInfo);
//            if (TextUtils.equals(event.getClassName(), "com.jifen.qkbase.main.MainActivity")) {
//                clickTest(nodeInfo);
//                new Thread(new Thr(nodeInfo)).start();
//                clickTest(nodeInfo);
//                useGestureClick(nodeInfo);
//                useGestureClick(nodeInfo);
//                useGestureClick(nodeInfo);
//            }

            if (!startThr2) {
                startThr2 = true;
//                new Thread(new Thr2()).start();
                new Thread(new ActionThr()).start();
            }
        }
    }

    private boolean startThr2 = false;

    private int time = 0;

//    private class Thr2 implements Runnable {
//
//        @Override
//        public void run() {
//            try {
//                while (true) {
//                    Thread.sleep(2000);
//                    Logger.d("工作状态:" + ActionTool.getInstance().getShouldAction());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }


//    private class Thr implements Runnable {
//        private AccessibilityNodeInfo nodeInfo;
//
//        Thr(AccessibilityNodeInfo nodeInfo) {
//            this.nodeInfo = nodeInfo;
//        }
//
//        @Override
//        public void run() {
//            try {
//                while (true) {
//                    Thread.sleep(10000);
//                    iterateNodesAndHandle(nodeInfo);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
//
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

    private class ActionThr implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    if (ActionTool.getInstance().getShouldAction()) {
                        if (time <= 120) {
                            scrollUp(nodeInfo);
                            Thread.sleep(2000);
                            scrollDown(nodeInfo);
                            Thread.sleep(2000);
                            time += 4;
                        } else {
                            //退出再进来
                            clickBack(nodeInfo);
                            Thread.sleep(1500);
                            clickIn(nodeInfo);
                            Thread.sleep(3000);
                            time = 0;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void scrollUp(AccessibilityNodeInfo nodeInfo) {
        Rect rect = new Rect();
        nodeInfo.getBoundsInScreen(rect);
        GestureDescription.Builder builder = new GestureDescription.Builder();
        Path path = new Path();
        path.moveTo(rect.centerX(), rect.centerY());
        path.lineTo(rect.centerX(), rect.centerY() - 300);
        GestureDescription gestureDescription = builder
                .addStroke(new GestureDescription.StrokeDescription(path, 100, 500))
                .build();
        dispatchGesture(gestureDescription, new AccessibilityService.GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
            }
        }, null);
    }

    public void scrollDown(AccessibilityNodeInfo nodeInfo) {
        Rect rect = new Rect();
        nodeInfo.getBoundsInScreen(rect);
        GestureDescription.Builder builder = new GestureDescription.Builder();
        Path path = new Path();
        path.moveTo(rect.centerX(), rect.centerY());
        path.lineTo(rect.centerX(), rect.centerY() + 300);
        GestureDescription gestureDescription = builder
                .addStroke(new GestureDescription.StrokeDescription(path, 100, 500))
                .build();
        dispatchGesture(gestureDescription, new AccessibilityService.GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
            }
        }, null);
    }

    public void clickBack(AccessibilityNodeInfo nodeInfo) {
        Rect rect = new Rect();
        nodeInfo.getBoundsInScreen(rect);
        GestureDescription.Builder builder = new GestureDescription.Builder();
        Path path = new Path();
        path.moveTo(20, 100);
        GestureDescription gestureDescription = builder
                .addStroke(new GestureDescription.StrokeDescription(path, 100, 50))
                .build();
        dispatchGesture(gestureDescription, new AccessibilityService.GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
            }
        }, null);
    }

    public void clickIn(AccessibilityNodeInfo nodeInfo) {
        Rect rect = new Rect();
        nodeInfo.getBoundsInScreen(rect);
        GestureDescription.Builder builder = new GestureDescription.Builder();
        Path path = new Path();
        path.moveTo(rect.centerX(), rect.centerY());
        GestureDescription gestureDescription = builder
                .addStroke(new GestureDescription.StrokeDescription(path, 100, 50))
                .build();
        dispatchGesture(gestureDescription, new AccessibilityService.GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
            }
        }, null);
    }


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

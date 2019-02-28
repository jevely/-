package com.jeve.gestures.action;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;

import com.jeve.gestures.Logger;

public class ActionTool {

    /**
     * 滑动
     */
    public static void scroll(AccessibilityNodeInfo nodeInfo, AccessibilityService service, int startX
            , int startY, int endX, int endY) {
        Rect rect = new Rect();
        nodeInfo.getBoundsInScreen(rect);
        GestureDescription.Builder builder = new GestureDescription.Builder();
        Path path = new Path();
        path.moveTo(startX, startY);
        path.lineTo(endX, endY);
        GestureDescription gestureDescription = builder
                .addStroke(new GestureDescription.StrokeDescription(path, 50, 500))
                .build();
        service.dispatchGesture(gestureDescription, new AccessibilityService.GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
            }
        }, null);
    }

    /**
     * 点击
     */
    public static void clickScreen(AccessibilityNodeInfo nodeInfo, AccessibilityService service, int clickX, int clickY) {
        Rect rect = new Rect();
        nodeInfo.getBoundsInScreen(rect);
        GestureDescription.Builder builder = new GestureDescription.Builder();
        Path path = new Path();
        path.moveTo(clickX, clickY);
        GestureDescription gestureDescription = builder
                .addStroke(new GestureDescription.StrokeDescription(path, 50, 50))
                .build();
        service.dispatchGesture(gestureDescription, new AccessibilityService.GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
            }
        }, null);
    }

    /**
     * 点击返回
     */
    public static void clickBack(AccessibilityService service) {
        Logger.d("点击返回");
        service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
    }
}

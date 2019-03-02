package com.jeve.gestures.action;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;

import com.jeve.gestures.content.AppContent;

public abstract class BaseAction {

    private String pakcageName;
    private long changeAppTime;
    private String changeAppPackageName;
    private long actionTime;

    private AppContent appContent;

    public String getPakcageName() {
        return pakcageName;
    }

    public void setPakcageName(String pakcageName) {
        this.pakcageName = pakcageName;
    }

    public long getChangeAppTime() {
        return changeAppTime;
    }

    public void setChangeAppTime(long changeAppTime) {
        this.changeAppTime = changeAppTime;
    }

    public String getChangeAppPackageName() {
        return changeAppPackageName;
    }

    public void setChangeAppPackageName(String changeAppPackageName) {
        this.changeAppPackageName = changeAppPackageName;
    }

    public long getActionTime() {
        return actionTime;
    }

    public void setActionTime(long actionTime) {
        this.actionTime = actionTime;
    }

    public AppContent getAppContent() {
        return appContent;
    }

    public BaseAction() {

    }

    public BaseAction(AppContent appContent) {
        this.appContent = appContent;
        pakcageName = appContent.getPackageName();
        changeAppPackageName = appContent.getChangePackageName();
        changeAppTime = appContent.getChangeTime();
        actionTime = appContent.getActionTime();
    }

    public void setAppContent(AppContent appContent) {
        this.appContent = appContent;
        pakcageName = appContent.getPackageName();
        changeAppPackageName = appContent.getChangePackageName();
        changeAppTime = appContent.getChangeTime();
        actionTime = appContent.getActionTime();
    }

    public abstract void checkAction(String className, AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception;
}

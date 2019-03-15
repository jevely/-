package com.jeve.gestures.action;

import android.accessibilityservice.AccessibilityService;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;

import com.jeve.gestures.content.AppContent;
import com.jeve.gestures.content.ContentManager;
import com.jeve.gestures.tool.ActionTool;
import com.jeve.gestures.tool.LocalLogTool;
import com.jeve.gestures.tool.Logger;
import com.jeve.gestures.tool.Utils;

import java.util.List;

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

    //主界面
    public void huiMainAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {

    }

    //新闻界面
    public void newsAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {

    }

    //视频界面
    public void videoAction(AccessibilityService service) throws Exception {

    }

    //其他界面 点击返回，退出至主界面
    public void otherAction(AccessibilityService service) throws Exception {
        LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "其他界面单次操作完毕");
        Thread.sleep(ActionManager.getInstance().clickBack);
        ActionTool.clickBack(service);
        recordTime(ActionManager.getInstance().clickBack);
    }

    //升级情况处理
    public void updateAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {

    }

    //广告下载情况处理
    public void adDownloadAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {

    }

    /**
     * 记录时间
     */
    public boolean recordTime(long time) throws Exception {
        setActionTime(getActionTime() + time);
        ContentManager.getInstance().changeContent(getAppContent());
        if (getChangeAppTime() != 0 && getActionTime() > getChangeAppTime() && !TextUtils.isEmpty(getChangeAppPackageName())
                && ActionManager.getInstance().hasNext(getPakcageName())) {
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "跳转:" + getChangeAppPackageName());
            Utils.startApp(getChangeAppPackageName());
            Thread.sleep(ActionManager.getInstance().appChange);
            return true;
        }
        return false;
    }

}

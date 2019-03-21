package com.jeve.gestures.action;

import android.accessibilityservice.AccessibilityService;
import android.graphics.Point;
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

    private float screenWidth;
    private float screenHeight;

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

    public float getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(float screenWidth) {
        this.screenWidth = screenWidth;
    }

    public float getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(float screenHeight) {
        this.screenHeight = screenHeight;
    }

    public BaseAction() {

    }

    public BaseAction(AppContent appContent) {
        this.appContent = appContent;
        pakcageName = appContent.getPackageName();
        changeAppPackageName = appContent.getChangePackageName();
        changeAppTime = appContent.getChangeTime();
        actionTime = appContent.getActionTime();

        Point point = Utils.getScreen();
        screenWidth = point.x;
        screenHeight = point.y;
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
        //升级弹框
        List<AccessibilityNodeInfo> moreView = nodeInfo.findAccessibilityNodeInfosByText("以后再说");
        if (moreView != null && !moreView.isEmpty()) {
            Logger.d(getAppContent().getAppName() + "首页-以后再说-意外界面");
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "首页-以后再说-意外界面");
            moreView.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
            Thread.sleep(1000);
        }

        //升级弹框
        List<AccessibilityNodeInfo> moreView2 = nodeInfo.findAccessibilityNodeInfosByText("升级领取福利");
        if (moreView2 != null && !moreView2.isEmpty()) {
            Logger.d(getAppContent().getAppName() + "首页-升级领取福利-意外界面");
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "首页-升级领取福利-意外界面");
            moreView2.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
            Thread.sleep(1000);
        }

        //领取奖励
        List<AccessibilityNodeInfo> moreView3 = nodeInfo.findAccessibilityNodeInfosByText("领取奖励");
        if (moreView3 != null && !moreView3.isEmpty()) {
            Logger.d(getAppContent().getAppName() + "首页-领取奖励-意外界面");
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "首页-领取奖励-意外界面");
            moreView3.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
            Thread.sleep(1000);
        }
    }

    //新闻界面
    public void newsAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {

    }

    //视频界面
    public void videoAction(AccessibilityService service) throws Exception {

    }

    //其他界面 点击返回，退出至主界面
    public void otherAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {

        //异常判断1
        List<AccessibilityNodeInfo> moreView = nodeInfo.findAccessibilityNodeInfosByText("知道了");
        if (moreView != null && !moreView.isEmpty()) {
            Logger.d(getAppContent().getAppName() + "其他页面-知道了-意外界面");
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "其他页面-知道了-意外界面");
            moreView.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
            Thread.sleep(1000);
        }

        //异常判断2
        List<AccessibilityNodeInfo> moreView2 = nodeInfo.findAccessibilityNodeInfosByText("取消");
        if (moreView2 != null && !moreView2.isEmpty()) {
            Logger.d(getAppContent().getAppName() + "其他页面-取消-意外界面");
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "其他页面-取消-意外界面");
            moreView2.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
            Thread.sleep(1000);
        }

        //异常判断3
        List<AccessibilityNodeInfo> moreView3 = nodeInfo.findAccessibilityNodeInfosByText("领取奖励");
        if (moreView3 != null && !moreView3.isEmpty()) {
            Logger.d(getAppContent().getAppName() + "其他页面-领取奖励-意外界面");
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "其他页面-领取奖励-意外界面");
            moreView3.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
            Thread.sleep(1000);
        }

        //异常判断4
        List<AccessibilityNodeInfo> moreView4 = nodeInfo.findAccessibilityNodeInfosByText("领取奖励");
        if (moreView3 != null && !moreView3.isEmpty()) {
            Logger.d(getAppContent().getAppName() + "其他页面-领取奖励-意外界面");
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "其他页面-领取奖励-意外界面");
            moreView3.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
            Thread.sleep(1000);
        }

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

    //点击左上角关闭返回按钮页面
    public void clickLeftTopToClose(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "点击左上角关闭返回按钮页面操作完毕");
        Thread.sleep(ActionManager.getInstance().clickBack);
        ActionTool.clickScreen(nodeInfo, service, (int) (getScreenWidth() / 53), (int) (getScreenWidth() / 10.8f));
        recordTime(ActionManager.getInstance().clickBack);
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

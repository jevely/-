package com.jeve.gestures.action;

import android.accessibilityservice.AccessibilityService;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;

import com.jeve.gestures.MyApplication;
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

    public void checkAction(String className, AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        repeatActionCheck(className, nodeInfo, service);
    }

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

        //忽略
        List<AccessibilityNodeInfo> moreView4 = nodeInfo.findAccessibilityNodeInfosByText("忽略");
        if (moreView4 != null && !moreView4.isEmpty()) {
            Logger.d(getAppContent().getAppName() + "首页-忽略-意外界面");
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "首页-忽略-意外界面");
            moreView4.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
            Thread.sleep(1000);
        }
    }

    //新闻界面
    public void newsAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {

    }

    //视频界面
    public void videoAction(AccessibilityService service) throws Exception {

    }

    //签到界面
    public void signAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        List<AccessibilityNodeInfo> moreView = nodeInfo.findAccessibilityNodeInfosByText("一键签到");
        if (moreView != null && !moreView.isEmpty()) {
            Logger.d(getAppContent().getAppName() + "一键签到");
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "一键签到");
            moreView.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
            Thread.sleep(1000);
            return;
        }

        List<AccessibilityNodeInfo> moreView2 = nodeInfo.findAccessibilityNodeInfosByText("立即去玩");
        if (moreView2 != null && !moreView2.isEmpty()) {
            Logger.d(getAppContent().getAppName() + "一键签到 立即去玩");
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "一键签到 立即去玩");
            ActionTool.clickBack(service);
            Thread.sleep(1000);
            return;
        }

        Logger.d(getAppContent().getAppName() + "一键签到 返回");
        LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "一键签到 返回");
        ActionTool.clickBack(service);
        Thread.sleep(1000);
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
            return;
        }

        //异常判断2
        List<AccessibilityNodeInfo> moreView2 = nodeInfo.findAccessibilityNodeInfosByText("取消");
        if (moreView2 != null && !moreView2.isEmpty()) {
            Logger.d(getAppContent().getAppName() + "其他页面-取消-意外界面");
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "其他页面-取消-意外界面");
            moreView2.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
            Thread.sleep(1000);
            return;
        }

        //异常判断3
        List<AccessibilityNodeInfo> moreView3 = nodeInfo.findAccessibilityNodeInfosByText("领取奖励");
        if (moreView3 != null && !moreView3.isEmpty()) {
            Logger.d(getAppContent().getAppName() + "其他页面-领取奖励-意外界面");
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "其他页面-领取奖励-意外界面");
            moreView3.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
            Thread.sleep(1000);
            return;
        }

        //异常判断4
        List<AccessibilityNodeInfo> moreView4 = nodeInfo.findAccessibilityNodeInfosByText("确定");
        if (moreView4 != null && !moreView4.isEmpty()) {
            Logger.d(getAppContent().getAppName() + "其他页面-确定-意外界面");
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "其他页面-确定-意外界面");
            moreView4.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
            Thread.sleep(1000);
            return;
        }

        //异常判断5
        List<AccessibilityNodeInfo> moreView5 = nodeInfo.findAccessibilityNodeInfosByText("继续阅读");
        if (moreView5 != null && !moreView5.isEmpty()) {
            Logger.d(getAppContent().getAppName() + "其他页面-继续阅读-意外界面");
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "其他页面-继续阅读-意外界面");
            moreView5.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
            Thread.sleep(1000);
            //点击切换界面
            ActionTool.clickScreen(nodeInfo, service, (int) (getScreenWidth() / 2), (int) (getScreenHeight() / 2f));
            Thread.sleep(1000);
            return;
        }

        //异常判断6
        List<AccessibilityNodeInfo> moreView6 = nodeInfo.findAccessibilityNodeInfosByText("忽略");
        if (moreView6 != null && !moreView6.isEmpty()) {
            Logger.d(getAppContent().getAppName() + "其他页面-忽略-意外界面");
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "其他页面-忽略-意外界面");
            moreView6.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
            Thread.sleep(1000);
            return;
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
        Logger.d(getAppContent().getAppName() + " getActionTime = " + getActionTime());
        ContentManager.getInstance().changeContent(getAppContent());
        Logger.d(getAppContent().getAppName() + "getChangeAppTime = " + getChangeAppTime() + "-- getActionTime = " + getActionTime() + "-- getChangeAppPackageName = " + getChangeAppPackageName());
        LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "getChangeAppTime = " + getChangeAppTime() + "-- getActionTime = " + getActionTime() + "-- getChangeAppPackageName = " + getChangeAppPackageName());
        if (getChangeAppTime() != 0 && getActionTime() > getChangeAppTime() && !TextUtils.isEmpty(getChangeAppPackageName())
                && ActionManager.getInstance().hasNext(getPakcageName())) {
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "跳转:" + getChangeAppPackageName());
            Logger.d("-------------------------------------");
            LocalLogTool.writeTxtToFile("-------------------------------------");
            Utils.startApp(getChangeAppPackageName());
            Thread.sleep(ActionManager.getInstance().appChange);
            return true;
        }
        Logger.d("-------------------------------------");
        LocalLogTool.writeTxtToFile("-------------------------------------");
        return false;
    }

    private String actionRepeatClassName;
    private int repeatCount = 0;

    /**
     * 检查重复操作
     */
    private void repeatActionCheck(String actionClassName, AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        if (TextUtils.equals(actionClassName, actionRepeatClassName)) {
            repeatCount++;
            Logger.d(getAppContent().getAppName() + "重复页面次数:" + repeatCount);
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "重复页面次数:" + repeatCount);
        } else {
            repeatCount = 0;
        }
        actionRepeatClassName = actionClassName;

        if (repeatCount == 8) {
            Logger.d(getAppContent().getAppName() + "重复页面点击返回测试:" + actionRepeatClassName);
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "重复页面点击返回测试:" + actionRepeatClassName);
            ActionTool.clickBack(service);
        }

        if (repeatCount == 12) {
            Logger.d(getAppContent().getAppName() + "重复页面点击左上角返回测试:" + actionRepeatClassName);
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "重复页面点击左上角返回测试:" + actionRepeatClassName);
            //点击左上角返回试试
            clickLeftTopToClose(nodeInfo, service);
        }

//        if (repeatCount == 16) {
//            Logger.d(getAppContent().getAppName() + "重复页面双击返回测试:" + actionRepeatClassName);
//            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "重复页面双击返回测试:" + actionRepeatClassName);
//            //其他界面 双击试试
//            ActionTool.clickBack(service);
//            Thread.sleep(400);
//            ActionTool.clickBack(service);
//        }

        if (repeatCount == 16) {
            Logger.d(getAppContent().getAppName() + "重复页面返回本APP再返回操作APP:" + actionRepeatClassName);
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "重复页面返回本APP再返回操作APP:" + actionRepeatClassName);
            Utils.startApp(MyApplication.getContext().getPackageName());
            Thread.sleep(6000);
//            ActionTool.clickBack(service);
//            Thread.sleep(2000);
//            ActionTool.clickBack(service);
            Utils.startApp(getAppContent().getOpenSelfPackageName());
            Thread.sleep(7000);
        }

        if (repeatCount >= 20) {
            //跳转下一个APP
            Logger.d(getAppContent().getAppName() + "重复页面:" + actionRepeatClassName);
            Logger.d(getAppContent().getAppName() + "重复跳转:" + getChangeAppPackageName());
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "重复页面:" + actionRepeatClassName);
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "重复跳转:" + getChangeAppPackageName());
            Utils.startApp(getChangeAppPackageName());
            repeatCount = 0;
        }
    }
}

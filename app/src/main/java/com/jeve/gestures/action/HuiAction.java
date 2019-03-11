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

/**
 * com.cashtoutiao.homepage.ui.dialog.PopInfoDialog 弹框退出
 * com.cashtoutiao.account.ui.main.MainTabActivity 主界面
 * com.cashtoutiao.news.ui.NewsDetailActivity 工作推荐 新闻
 * com.bytedance.sdk.openadsdk.activity.TTLandingPageActivity 广告
 * com.cashtoutiao.alivideodetail.AliVideoDetailActivity 视频
 */
public class HuiAction extends BaseAction {

    public HuiAction() {
        super();
    }

    public HuiAction(AppContent appContent) {
        super(appContent);
    }

    @Override
    public void checkAction(String className, AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        LocalLogTool.writeTxtToFile("进入惠头条 checkAction: " + className);
        switch (className) {
            case "com.cashtoutiao.account.ui.main.MainTabActivity":
                Logger.d("惠头条主界面操作");
                LocalLogTool.writeTxtToFile("惠头条主界面操作");
                huiMainAction(nodeInfo, service);
                break;
            case "com.cashtoutiao.news.ui.NewsDetailActivity":
                Logger.d("惠头条新闻界面操作");
                LocalLogTool.writeTxtToFile("惠头条新闻界面操作");
                newsAction(nodeInfo, service);
                break;
            case "com.cashtoutiao.alivideodetail.AliVideoDetailActivity":
                Logger.d("惠头条视频界面操作");
                LocalLogTool.writeTxtToFile("惠头条视频界面操作");
                videoAction(service);
                break;
            case "com.cashtoutiao.common.ui.SplashActivity":
                Logger.d("惠头条启动界面操作");
                LocalLogTool.writeTxtToFile("惠头条启动界面操作");
                setActionTime(0);
                ContentManager.getInstance().changeContent(getAppContent());
                break;
            case "com.cashtoutiao.common.ui.dialog.UpdateAppDialog":
                //升级情况
                Logger.d("惠头条升级界面操作");
                LocalLogTool.writeTxtToFile("惠头条升级界面操作");
                updateAction(nodeInfo, service);
                break;
            case "com.cashtoutiao.common.ui.dialog.CustomDialog":
                Logger.d("惠头条广告下载界面操作");
                LocalLogTool.writeTxtToFile("惠头条广告下载界面操作");
                adDownloadAction(nodeInfo, service);
                break;
            default:
                Logger.d("惠头条其他界面操作");
                LocalLogTool.writeTxtToFile("惠头条其他界面操作");
                otherAction(service);
                break;
        }
    }

    //主界面  滑动屏幕三分之一，点击
    private void huiMainAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        Thread.sleep(2000);
        ActionTool.scroll(nodeInfo, service, 540, 1280, 540, 640);
        Thread.sleep(1000);
        ActionTool.clickScreen(nodeInfo, service, 540, 960);
        LocalLogTool.writeTxtToFile("惠头条主界面单次操作完毕");
        recordTime(3000);
    }

    //新闻界面
    private void newsAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        Thread.sleep(2000);
        int time = 0;
        while (time < 120) {
            Thread.sleep(3000);
            ActionTool.scroll(nodeInfo, service, 540, 1280, 540, 880);
            Thread.sleep(3000);
            ActionTool.scroll(nodeInfo, service, 540, 880, 540, 1280);
            time += 6;
            LocalLogTool.writeTxtToFile("惠头条新闻单次操作完毕");
            if (recordTime(6000)) {
                return;
            }

        }
        Thread.sleep(2000);
        ActionTool.clickBack(service);
    }

    //视频界面
    private void videoAction(AccessibilityService service) throws Exception {
        Thread.sleep(30000);
        ActionTool.clickBack(service);
        LocalLogTool.writeTxtToFile("惠头条视频单次操作完毕");
        recordTime(30000);
    }

    //其他界面 点击返回，退出至主界面
    private void otherAction(AccessibilityService service) throws Exception {
        LocalLogTool.writeTxtToFile("惠头条其他界面单次操作完毕");
        Thread.sleep(ActionManager.getInstance().clickBack);
        ActionTool.clickBack(service);
    }

    //升级情况处理
    private void updateAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        Thread.sleep(2000);
        List<AccessibilityNodeInfo> moreView = nodeInfo.findAccessibilityNodeInfosByText("以后再说");
        if (moreView != null && moreView.size() == 1) {
            Logger.d("moreView = " + moreView.size());
            moreView.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
            LocalLogTool.writeTxtToFile("惠头条升级情况界面单次操作完毕");
        }
    }

    //广告下载情况处理
    private void adDownloadAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        Thread.sleep(2000);
        List<AccessibilityNodeInfo> moreView = nodeInfo.findAccessibilityNodeInfosByText("取消");
        if (moreView != null && moreView.size() == 1) {
            Logger.d("广告下载 = " + moreView.size());
            moreView.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
            LocalLogTool.writeTxtToFile("惠头条广告下载界面单次操作完毕");
        }
    }

    /**
     * 记录时间
     */
    private boolean recordTime(long time) throws Exception {
        setActionTime(getActionTime() + time);
        ContentManager.getInstance().changeContent(getAppContent());
        if (getChangeAppTime() != 0 && getActionTime() > getChangeAppTime() && !TextUtils.isEmpty(getChangeAppPackageName())
                && ActionManager.getInstance().hasNext(getPakcageName())) {
            LocalLogTool.writeTxtToFile("惠头条跳转:" + getChangeAppPackageName());
            Utils.startApp(getChangeAppPackageName());
            Thread.sleep(ActionManager.getInstance().appChange);
            return true;
        }
        return false;
    }
}

package com.jeve.gestures.action;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;

import com.jeve.gestures.Logger;

/**
 * com.cashtoutiao.homepage.ui.dialog.PopInfoDialog 弹框退出
 * com.cashtoutiao.account.ui.main.MainTabActivity 主界面
 * com.cashtoutiao.news.ui.NewsDetailActivity 工作推荐 新闻
 * com.bytedance.sdk.openadsdk.activity.TTLandingPageActivity 广告
 * com.cashtoutiao.alivideodetail.AliVideoDetailActivity 视频
 */
public class HuiAction {

    public void checkAction(String className, AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        switch (className) {
            case "com.cashtoutiao.account.ui.main.MainTabActivity":
                Logger.d("惠头条主界面操作");
                huiMainAction(nodeInfo, service);
                break;
            case "com.cashtoutiao.news.ui.NewsDetailActivity":
                Logger.d("惠头条新闻界面操作");
                newsAction(nodeInfo, service);
                break;
            case "com.cashtoutiao.alivideodetail.AliVideoDetailActivity":
                Logger.d("惠头条视频界面操作");
                videoAction(service);
                break;
            default:
                Logger.d("惠头条其他界面操作");
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
        }
        Thread.sleep(2000);
        ActionTool.clickBack(service);
    }

    //视频界面
    private void videoAction(AccessibilityService service) throws Exception {
        Thread.sleep(20000);
        ActionTool.clickBack(service);
    }

    //其他界面 点击返回，退出至主界面
    private void otherAction(AccessibilityService service) throws Exception {
        Thread.sleep(1000);
        ActionTool.clickBack(service);
    }
}

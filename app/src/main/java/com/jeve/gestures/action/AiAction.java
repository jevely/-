package com.jeve.gestures.action;

import android.accessibilityservice.AccessibilityService;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;

import com.jeve.gestures.content.AppContent;
import com.jeve.gestures.content.ContentManager;
import com.jeve.gestures.tool.ActionTool;
import com.jeve.gestures.tool.Logger;
import com.jeve.gestures.tool.Utils;

/**
 * 爱头条
 * com.aitoutiao.newsapp包名
 * <p>
 * com.news.hotheadlines.framwork.CoreActivity主页
 */
public class AiAction extends BaseAction {

    public AiAction() {
        super();
    }

    public AiAction(AppContent appContent) {
        super(appContent);
    }

    @Override
    public void checkAction(String className, AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        switch (className) {
            case "com.cashtoutiao.account.ui.main.MainTabActivity":
                Logger.d("爱头条主界面操作");
                huiMainAction(nodeInfo, service);
                break;
            case "com.cashtoutiao.news.ui.NewsDetailActivity":
                Logger.d("爱头条新闻界面操作");
                newsAction(nodeInfo, service);
                break;
            case "com.cashtoutiao.alivideodetail.AliVideoDetailActivity":
                Logger.d("爱头条视频界面操作");
                videoAction(service);
                break;
            case "com.cashtoutiao.common.ui.SplashActivity":
                Logger.d("爱头条启动界面操作");
                setActionTime(0);
                ContentManager.getInstance().changeContent(getAppContent());
                break;
            default:
                Logger.d("爱头条其他界面操作");
                otherAction(nodeInfo, service);
                break;
        }
    }

    //主界面  滑动屏幕三分之一，点击
    @Override
    public void huiMainAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        Thread.sleep(2000);
        ActionTool.scroll(nodeInfo, service, 540, 1280, 540, 640);
        Thread.sleep(1000);
        ActionTool.clickScreen(nodeInfo, service, 540, 960);

        recordTime(3000);
    }

    //新闻界面
    @Override
    public void newsAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        Thread.sleep(2000);
        int time = 0;
        while (time < 120) {
            Thread.sleep(3000);
            ActionTool.scroll(nodeInfo, service, 540, 1280, 540, 880);
            Thread.sleep(3000);
            ActionTool.scroll(nodeInfo, service, 540, 880, 540, 1280);
            time += 6;

            if (recordTime(6000)) {
                return;
            }

        }
        Thread.sleep(2000);
        ActionTool.clickBack(service);
    }

    //视频界面
    @Override
    public void videoAction(AccessibilityService service) throws Exception {
        Thread.sleep(30000);
        ActionTool.clickBack(service);
        recordTime(30000);
    }
}

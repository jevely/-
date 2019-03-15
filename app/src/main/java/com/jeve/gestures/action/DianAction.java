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

/**
 * 点点新闻
 * com.yingliang.clicknews包名
 * com.yingliang.clicknews.module.splash.SplashActivity启动页
 * com.bytedance.sdk.openadsdk.activity.TTDelegateActivity主界面
 * com.yingliang.clicknews.MainActivity主界面
 * <p>
 * android.widget.FrameLayout不操作
 * <p>
 * com.yingliang.clicknews.activity.NewsActivity新闻页
 */
public class DianAction extends BaseAction {

    public DianAction() {
        super();
    }

    public DianAction(AppContent appContent) {
        super(appContent);
    }

    @Override
    public void checkAction(String className, AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        LocalLogTool.writeTxtToFile("进入点点新闻 checkAction: " + className);
        switch (className) {
            case "com.yingliang.clicknews.MainActivity":
                Logger.d("点点新闻主界面操作");
                LocalLogTool.writeTxtToFile("点点新闻主界面操作");
                huiMainAction(nodeInfo, service);
                break;
            case "com.yingliang.clicknews.activity.NewsActivity":
                Logger.d("点点新闻新闻界面操作");
                LocalLogTool.writeTxtToFile("点点新闻新闻界面操作");
                newsAction(nodeInfo, service);
                break;
//            case "com.cashtoutiao.alivideodetail.AliVideoDetailActivity":
//                Logger.d("聚看点视频界面操作");
//                videoAction(service);
//                break;
            case "com.yingliang.clicknews.module.splash.SplashActivity":
                Logger.d("点点新闻启动页");
                LocalLogTool.writeTxtToFile("点点新闻启动界面操作");
                setActionTime(0);
                ContentManager.getInstance().changeContent(getAppContent());
                break;
            default:
                LocalLogTool.writeTxtToFile("点点新闻其他界面操作");
                Logger.d("点点新闻其他界面操作");
                otherAction(service);
                break;
        }
    }

    //主界面  滑动屏幕三分之一，点击
    @Override
    public void huiMainAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        super.huiMainAction(nodeInfo, service);
        Thread.sleep(2000);
        ActionTool.scroll(nodeInfo, service, 540, 1280, 540, 640);
        Thread.sleep(1000);
        ActionTool.clickScreen(nodeInfo, service, 540, 960);
        LocalLogTool.writeTxtToFile("点点新闻主界面单次操作完毕");
        recordTime(3000);
    }

    //新闻界面
    @Override
    public void newsAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        super.newsAction(nodeInfo, service);
        Thread.sleep(2000);
        int time = 0;
        while (time < 20) {
            ActionTool.scroll(nodeInfo, service, 540, 1620, 540, 520);
            Thread.sleep(5000);
            ActionTool.scroll(nodeInfo, service, 540, 1620, 540, 520);
            Thread.sleep(5000);
            time += 10;
            LocalLogTool.writeTxtToFile("点点新闻新闻单次操作完毕");
            if (recordTime(10000)) {
                return;
            }
        }
        Thread.sleep(2000);
        ActionTool.clickBack(service);
    }

}

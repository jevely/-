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
 * 大众头条
 * com.build.dazhong包名
 * <p>
 * com.build.dazhong.reconsitution.splash.SplashActivity启动页
 * <p>
 * com.bytedance.sdk.openadsdk.activity.TTDelegateActivity什么都不做
 * <p>
 * com.build.dazhong.reconsitution.home.activity.MainActivity_ 主页
 * <p>
 * com.build.dazhong.reconsitution.news.activity.NewsDetailListActivity新闻
 */
public class DaAction extends BaseAction {

    public DaAction() {
        super();
    }

    public DaAction(AppContent appContent) {
        super(appContent);
    }

    @Override
    public void checkAction(String className, AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        LocalLogTool.writeTxtToFile("进入大众头条 checkAction: " + className);
        super.checkAction(className, nodeInfo, service);
        switch (className) {
            case "com.build.dazhong.reconsitution.home.activity.MainActivity_":
                Logger.d("大众头条主界面操作");
                LocalLogTool.writeTxtToFile("大众头条主界面操作");
                huiMainAction(nodeInfo, service);
                break;
            case "com.build.dazhong.reconsitution.news.activity.NewsDetailListActivity":
                Logger.d("大众头条新闻界面操作");
                LocalLogTool.writeTxtToFile("大众头条新闻界面操作");
                newsAction(nodeInfo, service);
                break;
            case "com.build.dazhong.reconsitution.video.activity.VideoDetailActivity_":
                Logger.d("大众头条视频界面操作");
                LocalLogTool.writeTxtToFile("大众头条视频界面操作");
                videoAction(service);
                break;
            case "com.build.dazhong.reconsitution.splash.SplashActivity":
                LocalLogTool.writeTxtToFile("大众头条启动界面操作");
                Logger.d("大众头条启动界面操作");
                setActionTime(0);
                ContentManager.getInstance().changeContent(getAppContent());
                break;
            default:
                LocalLogTool.writeTxtToFile("大众头条其他界面操作");
                Logger.d("大众头条其他界面操作");
                otherAction(nodeInfo, service);
                break;
        }
    }

    //主界面  滑动屏幕三分之一，点击
    @Override
    public void huiMainAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        super.huiMainAction(nodeInfo, service);
        Thread.sleep(2000);

        List<AccessibilityNodeInfo> moreView = nodeInfo.findAccessibilityNodeInfosByText("先去逛逛");
        if (moreView != null && !moreView.isEmpty()) {
            Logger.d("大众头条首页意外界面");
            LocalLogTool.writeTxtToFile("大众头条首页意外界面");
            ActionTool.clickBack(service);
            Thread.sleep(1000);
        }

        List<AccessibilityNodeInfo> moreView2 = nodeInfo.findAccessibilityNodeInfosByText("后面还有");
        if (moreView2 != null && !moreView2.isEmpty()) {
            Logger.d(getAppContent().getAppName() + "大众头条首页-后面还有-意外界面");
            LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "大众头条首页-后面还有-意外界面");
            ActionTool.clickBack(service);
            Thread.sleep(1000);
        }

        Logger.d("大众头条正常界面");
        ActionTool.scroll(nodeInfo, service, (int) (getScreenWidth() / 2), (int) (getScreenHeight() / 1.5f), (int) (getScreenWidth() / 2), (int) (getScreenHeight() / 3));
        Thread.sleep(1000);
        ActionTool.clickScreen(nodeInfo, service, (int) (getScreenWidth() / 2), (int) (getScreenHeight() / 2f));
        Logger.d("大众头条主页操作完毕");
        LocalLogTool.writeTxtToFile("大众头条主界面单次操作完毕");
        recordTime(3000);
    }

    //新闻界面
    @Override
    public void newsAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        super.newsAction(nodeInfo, service);
        Thread.sleep(2000);
        int time = 0;
        while (time < 35) {
            ActionTool.scroll(nodeInfo, service, (int) (getScreenWidth() / 2), (int) (getScreenHeight() / 1.5f), (int) (getScreenWidth() / 2), (int) (getScreenHeight() / 2.18f));
            Thread.sleep(1000);
            ActionTool.scroll(nodeInfo, service, (int) (getScreenWidth() / 2), (int) (getScreenHeight() / 2.18f), (int) (getScreenWidth() / 2), (int) (getScreenHeight() / 1.5f));
            Thread.sleep(4000);
            time += 5;
            if (recordTime(5000)) {
                return;
            }
        }
        LocalLogTool.writeTxtToFile("大众头条新闻单次操作完毕");
        Logger.d("大众头条新闻单次操作完毕");
        Thread.sleep(2000);
        ActionTool.clickBack(service);
    }

    //视频界面
    @Override
    public void videoAction(AccessibilityService service) throws Exception {
        super.videoAction(service);
        Thread.sleep(30000);
        ActionTool.clickBack(service);
        Logger.d("大众头条视频单次操作完毕");
        LocalLogTool.writeTxtToFile("大众头条视频单次操作完毕");
        recordTime(30000);
    }

}


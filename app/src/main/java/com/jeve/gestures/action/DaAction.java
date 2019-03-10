package com.jeve.gestures.action;

import android.accessibilityservice.AccessibilityService;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;

import com.jeve.gestures.content.AppContent;
import com.jeve.gestures.content.ContentManager;
import com.jeve.gestures.tool.ActionTool;
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
        switch (className) {
            case "com.build.dazhong.reconsitution.home.activity.MainActivity_":
                Logger.d("大众头条主界面操作");
                huiMainAction(nodeInfo, service);
                break;
            case "com.build.dazhong.reconsitution.news.activity.NewsDetailListActivity":
                Logger.d("大众头条新闻界面操作");
                newsAction(nodeInfo, service);
                break;
//            case "com.cashtoutiao.alivideodetail.AliVideoDetailActivity":
//                Logger.d("大众头条视频界面操作");
//                videoAction(service);
//                break;
            case "com.build.dazhong.reconsitution.splash.SplashActivity":
                Logger.d("大众头条启动界面操作");
                setActionTime(0);
                ContentManager.getInstance().changeContent(getAppContent());
                break;
            default:
                Logger.d("大众头条其他界面操作");
                otherAction(service);
                break;
        }
    }

    //主界面  滑动屏幕三分之一，点击
    private void huiMainAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {

        Thread.sleep(2000);
        List<AccessibilityNodeInfo> moreView = nodeInfo.findAccessibilityNodeInfosByText("先去逛逛");
        if (moreView != null && !moreView.isEmpty()) {
            Logger.d("大众头条首页意外界面");
            ActionTool.clickBack(service);
            Thread.sleep(1000);
        }
        Logger.d("大众头条正常界面");
        ActionTool.scroll(nodeInfo, service, 540, 1280, 540, 640);
        Thread.sleep(1000);
        ActionTool.clickScreen(nodeInfo, service, 540, 960);
        Logger.d("大众头条主页操作完毕");
        recordTime(3000);
    }

    //新闻界面
    private void newsAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        Thread.sleep(2000);
        int time = 0;
        while (time < 35) {
            ActionTool.scroll(nodeInfo, service, 540, 1280, 540, 880);
            Thread.sleep(1000);
            ActionTool.scroll(nodeInfo, service, 540, 880, 540, 1280);
            Thread.sleep(4000);
            time += 5;

            if (recordTime(5000)) {
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
        recordTime(30000);
    }

    //其他界面 点击返回，退出至主界面
    private void otherAction(AccessibilityService service) throws Exception {
        Thread.sleep(ActionManager.getInstance().clickBack);
        ActionTool.clickBack(service);
    }

    /**
     * 记录时间
     */
    private boolean recordTime(long time) throws Exception {
        setActionTime(getActionTime() + time);
        ContentManager.getInstance().changeContent(getAppContent());
        if (getChangeAppTime() != 0 && getActionTime() > getChangeAppTime() && !TextUtils.isEmpty(getChangeAppPackageName())
                && ActionManager.getInstance().hasNext(getPakcageName())) {
            Utils.startApp(getChangeAppPackageName());
            Thread.sleep(ActionManager.getInstance().appChange);
            return true;
        }
        return false;
    }
}


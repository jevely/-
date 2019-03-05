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
 * 花生头条
 * com.xcm.huasheng包名
 * com.xcm.huasheng.ui.activity.SplashActivity启动页
 * com.xcm.huasheng.ui.activity.MainActivity首页
 * <p>
 * com.xcm.huasheng.ui.activity.NewsDetailActivity新闻页面
 * com.xcm.huasheng.ui.activity.VideoDetailActivity视频页面
 */
public class HuaAction extends BaseAction {

    public HuaAction() {
        super();
    }

    public HuaAction(AppContent appContent) {
        super(appContent);
    }

    @Override
    public void checkAction(String className, AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        switch (className) {
            case "com.xcm.huasheng.ui.activity.MainActivity":
                Logger.d("花生头条主界面操作");
                huiMainAction(nodeInfo, service);
                break;
            case "com.xcm.huasheng.ui.activity.NewsDetailActivity":
                Logger.d("花生头条新闻界面操作");
                newsAction(nodeInfo, service);
                break;
            case "com.xcm.huasheng.ui.activity.VideoDetailActivity":
                Logger.d("花生头条视频界面操作");
                videoAction(service);
                break;
            case "com.xcm.huasheng.ui.activity.SplashActivity":
                Logger.d("花生头条启动界面操作");
                setActionTime(0);
                ContentManager.getInstance().changeContent(getAppContent());
                break;
            default:
                Logger.d("花生头条其他界面操作");
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

        recordTime(3000);
    }

    //新闻界面
    private void newsAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        Thread.sleep(2000);
        int time = 0;
        while (time < 33) {
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
    private void videoAction(AccessibilityService service) throws Exception {
        Thread.sleep(31000);
        ActionTool.clickBack(service);
        recordTime(31000);
    }

    //其他界面 点击返回，退出至主界面
    private void otherAction(AccessibilityService service) throws Exception {
        Thread.sleep(1000);
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
            Thread.sleep(15000);
            return true;
        }
        return false;
    }
}

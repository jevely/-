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
 * com.jm.video.ui.main.MainActivity视频页面
 */
public class ShuaAction extends BaseAction {

    public ShuaAction() {
        super();
    }

    public ShuaAction(AppContent appContent) {
        super(appContent);
    }

    @Override
    public void checkAction(String className, AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        LocalLogTool.writeTxtToFile("进入刷宝 checkAction: " + className);
        switch (className) {
            case "com.jm.video.ui.main.MainActivity":
                Logger.d("刷宝主界面操作");
                LocalLogTool.writeTxtToFile("刷宝主界面操作");
                huiMainAction(nodeInfo, service);
                break;
            case "com.jm.video.ui.main.SplashActivity":
            case "com.jm.video.ui.main.AdsActivity":
                setActionTime(0);
                LocalLogTool.writeTxtToFile("刷宝冷启动界面操作");
                ContentManager.getInstance().changeContent(getAppContent());
                break;
            default:
                Logger.d("刷宝其他界面操作");
                LocalLogTool.writeTxtToFile("刷宝其他界面操作");
                otherAction(nodeInfo,service);
                break;
        }
    }

    //刷宝主界面 滑动 点击 等待
    @Override
    public void huiMainAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        super.huiMainAction(nodeInfo, service);
        Thread.sleep(10000);
        ActionTool.scroll(nodeInfo, service, 540, 350, 540, 1650);
        setActionTime(getActionTime() + 10000);
        ContentManager.getInstance().changeContent(getAppContent());
        LocalLogTool.writeTxtToFile("刷宝主界面单次操作完毕");

        //一个小时后跳转新闻
        if (getChangeAppTime() != 0 && getActionTime() > getChangeAppTime() && !TextUtils.isEmpty(getChangeAppPackageName())
                && ActionManager.getInstance().hasNext(getPakcageName())) {
            LocalLogTool.writeTxtToFile("刷宝跳转:" + getChangeAppPackageName());
            Utils.startApp(getChangeAppPackageName());
            Thread.sleep(15000);
        }
    }

}

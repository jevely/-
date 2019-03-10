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

        switch (className) {
            case "com.jm.video.ui.main.MainActivity":
                Logger.d("刷宝主界面操作");
                shuaMainAction(nodeInfo, service);
                break;
            case "com.jm.video.ui.main.SplashActivity":
            case "com.jm.video.ui.main.AdsActivity":
                setActionTime(0);
                ContentManager.getInstance().changeContent(getAppContent());
                break;
            default:
                Logger.d("刷宝其他界面操作");
                otherAction(service);
                break;
        }
    }

    //刷宝主界面 滑动 点击 等待
    private void shuaMainAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        Thread.sleep(10000);
        ActionTool.scroll(nodeInfo, service, 540, 350, 540, 1650);
        setActionTime(getActionTime() + 10000);
        ContentManager.getInstance().changeContent(getAppContent());
        //一个小时后跳转新闻
        if (getChangeAppTime() != 0 && getActionTime() > getChangeAppTime() && !TextUtils.isEmpty(getChangeAppPackageName())
                && ActionManager.getInstance().hasNext(getPakcageName())) {
            Utils.startApp(getChangeAppPackageName());
            Thread.sleep(15000);
        }
    }

    //其他界面 点击返回，退出至主界面
    private void otherAction(AccessibilityService service) throws Exception {
        Thread.sleep(ActionManager.getInstance().clickBack);
        ActionTool.clickBack(service);
    }

}

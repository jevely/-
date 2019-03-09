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
 * 包名:com.xiangzi.jukandian
 * <p>
 * com.xiangzi.jukandian.activity.V2WelcomeActivity欢迎页
 * com.xiangzi.jukandian.activity.MainActivity 首页
 * com.xiangzi.jukandian.widget.dialog.UserIsRegDialog弹框
 * com.xiangzi.jukandian.widget.dialog.QuitNewUserDialog弹框
 * <p>
 * com.xiangzi.jukandian.activity.NativeArticalDetailActivity新闻
 * com.xiangzi.jukandian.activity.WebViewActivity新闻
 * <p>
 * com.xiangzinet.jkd.JKDActivity广告
 */
public class KanAction extends BaseAction {

    public KanAction() {
        super();
    }

    public KanAction(AppContent appContent) {
        super(appContent);
    }

    @Override
    public void checkAction(String className, AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        switch (className) {
            case "com.xiangzi.jukandian.activity.MainActivity":
                Logger.d("聚看点主界面操作");
                huiMainAction(nodeInfo, service);
                break;
            case "com.xiangzi.jukandian.activity.NativeArticalDetailActivity":
            case "com.xiangzi.jukandian.activity.WebViewActivity":
                Logger.d("聚看点新闻界面操作");
                newsAction(nodeInfo, service);
                break;
//            case "com.cashtoutiao.alivideodetail.AliVideoDetailActivity":
//                Logger.d("惠头条视频界面操作");
//                videoAction(service);
//                break;
            case "com.xiangzi.jukandian.activity.V2WelcomeActivity":
                Logger.d("聚看点欢迎界面");
                setActionTime(0);
                ContentManager.getInstance().changeContent(getAppContent());
                break;
            default:
                Logger.d("聚看点其他界面操作");
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
        while (time < 90) {
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
        Thread.sleep(20000);
        ActionTool.clickBack(service);
        recordTime(20000);
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
            Thread.sleep(15000);
            return true;
        }
        return false;
    }
}


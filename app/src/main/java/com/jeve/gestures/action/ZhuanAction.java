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
 * com.yj.yueyouqian包名
 * <p>
 * com.jifen.qukan.ui.activity.PermissionCheckActivity启动页
 * <p>
 * com.abase.view.weight.MyDialog不操作
 * <p>
 * com.jifen.qukan.ui.activity.HomeActivity主界面
 * <p>
 * com.jifen.qukan.module.channel.news.list.detail.NewsDetailActivity新闻界面
 */
public class ZhuanAction extends BaseAction {

    public ZhuanAction() {
        super();
    }

    public ZhuanAction(AppContent appContent) {
        super(appContent);
    }

    @Override
    public void checkAction(String className, AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        switch (className) {
            case "com.jifen.qukan.ui.activity.HomeActivity":
                Logger.d("赚钱阅有钱主界面操作");
                huiMainAction(nodeInfo, service);
                break;
            case "com.jifen.qukan.module.channel.news.list.detail.NewsDetailActivity":
                Logger.d("赚钱阅有钱新闻界面操作");
                newsAction(nodeInfo, service);
                break;
//            case "com.cashtoutiao.alivideodetail.AliVideoDetailActivity":
//                Logger.d("赚钱阅有钱视频界面操作");
//                videoAction(service);
//                break;
            case "com.jifen.qukan.ui.activity.PermissionCheckActivity":
                setActionTime(0);
                ContentManager.getInstance().changeContent(getAppContent());
                break;
            default:
                Logger.d("赚钱阅有钱其他界面操作");
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
        while (time < 35) {
            Thread.sleep(3000);
            ActionTool.scroll(nodeInfo, service, 540, 1280, 540, 880);
            Thread.sleep(1000);
            ActionTool.scroll(nodeInfo, service, 540, 880, 540, 1280);
            time += 4;

            if (recordTime(4000)) {
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


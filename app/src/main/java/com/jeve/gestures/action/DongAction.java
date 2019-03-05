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
 * 东方头条
 * com.songheng.eastnews包名
 * com.oa.eastfirst.activity.WelcomeActivity欢迎页面
 * com.songheng.eastfirst.common.view.widget.dialog.ImageGalleryActivityDialog领钱dialog
 * com.songheng.eastfirst.common.view.activity.MainActivity主界面
 * com.songheng.eastfirst.business.newstopic.view.activity.NewsTopicActivity新闻列表  直接退出
 * com.songheng.eastfirst.common.view.activity.WebViewActivity直接退出
 * <p>
 * com.songheng.eastfirst.business.newsdetail.view.activity.NewsDetailH5Activity新闻页面
 * com.songheng.eastfirst.business.video.view.activity.VideoDetailActivity视频页面
 */
public class DongAction extends BaseAction {

    public DongAction() {
        super();
    }

    public DongAction(AppContent appContent) {
        super(appContent);
    }

    @Override
    public void checkAction(String className, AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        switch (className) {
            case "com.songheng.eastfirst.common.view.activity.MainActivity":
                Logger.d("东方头条主界面操作");
                huiMainAction(nodeInfo, service);
                break;
            case "com.songheng.eastfirst.business.newsdetail.view.activity.NewsDetailH5Activity":
                Logger.d("东方头条新闻界面操作");
                newsAction(nodeInfo, service);
                break;
            case "com.songheng.eastfirst.business.video.view.activity.VideoDetailActivity":
                Logger.d("东方头条视频界面操作");
                videoAction(service);
                break;
            case "com.oa.eastfirst.activity.WelcomeActivity":
                Logger.d("东方头条启动界面操作");
                setActionTime(0);
                ContentManager.getInstance().changeContent(getAppContent());
                break;
            default:
                Logger.d("东方头条其他界面操作");
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
        while (time < 60) {
            Thread.sleep(12000);
            ActionTool.scroll(nodeInfo, service, 540, 1620, 540, 620);
            Thread.sleep(1000);
            ActionTool.scroll(nodeInfo, service, 540, 1620, 540, 620);
            time += 10;

            if (recordTime(10000)) {
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

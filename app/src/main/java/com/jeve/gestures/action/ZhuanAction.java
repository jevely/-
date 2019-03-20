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
        LocalLogTool.writeTxtToFile("进入赚钱阅有钱 checkAction: " + className);
        switch (className) {
            case "com.jifen.qukan.ui.activity.HomeActivity":
                Logger.d("赚钱阅有钱主界面操作");
                LocalLogTool.writeTxtToFile("赚钱阅有钱主界面操作");
                huiMainAction(nodeInfo, service);
                break;
            case "com.jifen.qukan.module.channel.news.list.detail.NewsDetailActivity":
                Logger.d("赚钱阅有钱新闻界面操作");
                LocalLogTool.writeTxtToFile("赚钱阅有钱新闻界面操作");
                newsAction(nodeInfo, service);
                break;
//            case "com.cashtoutiao.alivideodetail.AliVideoDetailActivity":
//                Logger.d("赚钱阅有钱视频界面操作");
//                videoAction(service);
//                break;
            case "com.jifen.qukan.ui.activity.PermissionCheckActivity":
                LocalLogTool.writeTxtToFile("赚钱阅有钱冷启动界面操作");
                setActionTime(0);
                ContentManager.getInstance().changeContent(getAppContent());
                break;
            default:
                Logger.d("赚钱阅有钱其他界面操作");
                LocalLogTool.writeTxtToFile("赚钱阅有钱其他界面操作");
                otherAction(nodeInfo, service);
                break;
        }
    }

    //主界面  滑动屏幕三分之一，点击
    @Override
    public void huiMainAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
//        //弹框检测
//        List<AccessibilityNodeInfo> moreView = nodeInfo.findAccessibilityNodeInfosByText("立即领取");
//        Logger.d("moreView = " + moreView.size());
        super.huiMainAction(nodeInfo, service);
        Thread.sleep(2000);
        ActionTool.scroll(nodeInfo, service, 540, 1280, 540, 640);
        Thread.sleep(1000);
        ActionTool.clickScreen(nodeInfo, service, 540, 960);
        LocalLogTool.writeTxtToFile("赚钱阅有钱主界面单次操作完毕");
        recordTime(3000);
    }

    //新闻界面
    @Override
    public void newsAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        super.newsAction(nodeInfo, service);
        Thread.sleep(2000);
        int time = 0;
        while (time < 35) {
            Thread.sleep(3000);
            ActionTool.scroll(nodeInfo, service, 540, 1280, 540, 880);
            Thread.sleep(1000);
            ActionTool.scroll(nodeInfo, service, 540, 880, 540, 1280);
            time += 4;
            LocalLogTool.writeTxtToFile("赚钱阅有钱新闻单次操作完毕");
            if (recordTime(4000)) {
                return;
            }
        }
        Thread.sleep(2000);
        ActionTool.clickBack(service);
    }

}


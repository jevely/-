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
 * 中青看点
 * cn.youth.news包名
 * <p>
 * com.weishang.wxrd.activity.MainActivity主界面
 * com.weishang.wxrd.ui.RedPacketFirstActivity主界面
 * <p>
 * com.weishang.wxrd.activity.WebViewActivity新闻界面
 * <p>
 * com.weishang.wxrd.activity.SplashActivity冷启动
 */
public class ZhongAction extends BaseAction {

    public ZhongAction() {
        super();
    }

    public ZhongAction(AppContent appContent) {
        super(appContent);
    }

    @Override
    public void checkAction(String className, AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        LocalLogTool.writeTxtToFile("进入中青看点 checkAction: " + className);
        super.checkAction(className, nodeInfo, service);
        switch (className) {
            case "com.weishang.wxrd.activity.MainActivity":
            case "com.weishang.wxrd.ui.RedPacketFirstActivity":
                Logger.d("中青看点主界面操作");
                LocalLogTool.writeTxtToFile("中青看点主界面操作");
                huiMainAction(nodeInfo, service);
                break;
            case "com.weishang.wxrd.activity.WebViewActivity":
                Logger.d("中青看点新闻界面操作");
                LocalLogTool.writeTxtToFile("中青看点新闻界面操作");
                newsAction(nodeInfo, service);
                break;
//            case "com.cashtoutiao.alivideodetail.AliVideoDetailActivity":
//                Logger.d("惠头条视频界面操作");
//                LocalLogTool.writeTxtToFile("惠头条视频界面操作");
//                videoAction(service);
//                break;
            case "com.weishang.wxrd.activity.SplashActivity":
                Logger.d("中青看点启动界面操作");
                LocalLogTool.writeTxtToFile("中青看点启动界面操作");
                setActionTime(0);
                ContentManager.getInstance().changeContent(getAppContent());
                break;
            default:
                Logger.d("中青看点其他界面操作");
                LocalLogTool.writeTxtToFile("中青看点其他界面操作");
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
            Logger.d("中青看点首页意外界面");
            LocalLogTool.writeTxtToFile("中青看点首页意外界面");
            ActionTool.clickBack(service);
            Thread.sleep(1000);
        }

        List<AccessibilityNodeInfo> moreView2 = nodeInfo.findAccessibilityNodeInfosByText("残忍拒绝");
        if (moreView2 != null && !moreView2.isEmpty()) {
            Logger.d("中青看点首页意外界面2");
            LocalLogTool.writeTxtToFile("中青看点首页意外界面2");
            ActionTool.clickBack(service);
            Thread.sleep(1000);
        }

        ActionTool.scroll(nodeInfo, service, (int) (getScreenWidth() / 2), (int) (getScreenHeight() / 1.5f), (int) (getScreenWidth() / 2), (int) (getScreenHeight() / 3));
        Thread.sleep(1000);
        ActionTool.clickScreen(nodeInfo, service, (int) (getScreenWidth() / 2), (int) (getScreenHeight() / 2f));
        LocalLogTool.writeTxtToFile("中青看点主界面单次操作完毕");
        recordTime(3000);
    }

    //新闻界面
    @Override
    public void newsAction(AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        super.newsAction(nodeInfo, service);

        Thread.sleep(2000);

        int time = 0;
        while (time < 120) {

            //奖励弹框
            List<AccessibilityNodeInfo> moreView = nodeInfo.findAccessibilityNodeInfosByText("阅读5篇后还有");
            if (moreView != null && !moreView.isEmpty()) {
                Logger.d(getAppContent().getAppName() + "首页-阅读5篇后还有-意外界面");
                LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "首页-阅读5篇后还有-意外界面");
                moreView.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                Thread.sleep(1000);
            }

            //奖励弹框
            List<AccessibilityNodeInfo> moreView2 = nodeInfo.findAccessibilityNodeInfosByText("忽略");
            if (moreView2 != null && !moreView2.isEmpty()) {
                Logger.d(getAppContent().getAppName() + "-忽略-意外界面");
                LocalLogTool.writeTxtToFile(getAppContent().getAppName() + "-忽略-意外界面");
                moreView2.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }

            Thread.sleep(3000);
            ActionTool.scroll(nodeInfo, service, (int) (getScreenWidth() / 2), (int) (getScreenHeight() / 1.5f), (int) (getScreenWidth() / 2), (int) (getScreenHeight() / 2.18f));
            Thread.sleep(3000);
            ActionTool.scroll(nodeInfo, service, (int) (getScreenWidth() / 2), (int) (getScreenHeight() / 2.18f), (int) (getScreenWidth() / 2), (int) (getScreenHeight() / 1.5f));
            time += 6;
            if (recordTime(6000)) {
                return;
            }

        }
        LocalLogTool.writeTxtToFile("中青看点新闻单次操作完毕");
        Thread.sleep(2000);
        ActionTool.clickBack(service);
    }

}

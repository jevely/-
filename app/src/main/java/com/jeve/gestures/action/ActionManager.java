package com.jeve.gestures.action;

import android.accessibilityservice.AccessibilityService;
import android.text.LoginFilter;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;

import com.jeve.gestures.content.AppContent;
import com.jeve.gestures.content.ContentManager;
import com.jeve.gestures.tool.ActionTool;
import com.jeve.gestures.tool.LocalLogTool;
import com.jeve.gestures.tool.Logger;

import java.util.ArrayList;
import java.util.List;

public class ActionManager {

    private List<AppContent> actionList;

    private ActionManager() {
        actionList = new ArrayList<>();
    }

    public long clickBack = 2000;
    public long appChange = 20000;

    private static class ActionBulder {
        private final static ActionManager manager = new ActionManager();
    }

    public static ActionManager getInstance() {
        return ActionBulder.manager;
    }

    public void doAction(String pakcageName, String className, AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {

        for (AppContent content : actionList) {
            if (content.getPackageName().contains(pakcageName)) {
                Logger.d("开始执行action class = " + className);
                LocalLogTool.writeTxtToFile("循环找到APP：" + className);
                //开始执行
                BaseAction baseAction = ContentManager.getInstance().getAction(content);
                if (baseAction != null) baseAction.checkAction(className, nodeInfo, service);
                return;
            }
        }

        //都不是，可能跳转到其他APP，点击返回
        Logger.d("跳转到其他APP:" + className);
        otherAction(service);

    }

    //其他界面 点击返回，退出至主界面
    private void otherAction(AccessibilityService service) throws Exception {
        Thread.sleep(clickBack);
        ActionTool.clickBack(service);
    }

    public void addActionContent(AppContent appContent) {
        if (!actionList.contains(appContent)) {
            actionList.add(appContent);
        }
    }

    public void removeActionContent(AppContent appContent) {
        actionList.remove(appContent);
    }

    public List<AppContent> getAppList() {
        return actionList;
    }

    public boolean hasNext(String packageName) {
        int a = 0;
        boolean hasContent = false;
        for (AppContent appContent : actionList) {
            if (TextUtils.equals(packageName, appContent.getPackageName())) {
                hasContent = true;
            }

            if (hasContent) {
                a++;
            }
        }

        return a > 0;
    }

}

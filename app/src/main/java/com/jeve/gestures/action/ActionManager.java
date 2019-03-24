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
import com.jeve.gestures.tool.Utils;

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
                currentRightApp = pakcageName;
                //开始执行
                BaseAction baseAction = ContentManager.getInstance().getAction(content);
                if (baseAction != null) baseAction.checkAction(className, nodeInfo, service);
                return;
            }
        }

        //都不是，可能跳转到其他APP，双击返回
        Logger.d("跳转到其他APP:" + className);
        LocalLogTool.writeTxtToFile("跳转到其他APP：" + className);
        otherRepeateAction(className, service);

    }

    //其他界面 单击返回
    private void otherAction1(AccessibilityService service) throws Exception {
        Thread.sleep(clickBack);
        ActionTool.clickBack(service);
    }

    //其他界面 双击返回
    private void otherAction2(AccessibilityService service) throws Exception {
        Thread.sleep(clickBack);
        ActionTool.clickBack(service);
        Thread.sleep(400);
        ActionTool.clickBack(service);
    }

    //其他APP没有跳转回来处理
    private String otherAppPackageName;
    private int otherRepeateCount = 0;
    private String currentRightApp;

    private void otherRepeateAction(String className, AccessibilityService service) throws Exception {
        Thread.sleep(2000);
        if (TextUtils.equals(otherAppPackageName, className)) {
            otherRepeateCount++;
        } else {
            otherRepeateCount = 0;
        }
        otherAppPackageName = className;
        if (otherRepeateCount == 4) {
            Logger.d("其他APP点击返回处理:" + className);
            LocalLogTool.writeTxtToFile("其他APP点击返回处理：" + className);
            otherAction1(service);
        }
        if (otherRepeateCount == 7) {
            Logger.d("其他APP双击击返回处理:" + className);
            LocalLogTool.writeTxtToFile("其他APP双击击返回处理：" + className);
            otherAction2(service);
        }
        if (otherRepeateCount == 10) {
            if (!TextUtils.isEmpty(currentRightApp)) {
                Logger.d("直接返回正确APP:" + currentRightApp + "--" + className);
                LocalLogTool.writeTxtToFile("直接返回正确APP:" + currentRightApp + "--" + className);
                Utils.startApp(currentRightApp);
                otherRepeateCount = 0;
            }
        }
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

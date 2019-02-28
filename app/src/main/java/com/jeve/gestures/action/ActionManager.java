package com.jeve.gestures.action;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;

import com.jeve.gestures.Logger;

public class ActionManager {

    private HuiAction huiAction;
    private ShuaAction shuaAction;

    private ActionManager() {
        huiAction = new HuiAction();
        shuaAction = new ShuaAction();
    }

    private static class ActionBulder {
        private final static ActionManager manager = new ActionManager();
    }

    public static ActionManager getInstance() {
        return ActionBulder.manager;
    }

    public void doAction(String pakcageName, String className, AccessibilityNodeInfo nodeInfo, AccessibilityService service) throws Exception {
        if (checkHui(pakcageName)) {
            Logger.d("开始操作惠头条");
            huiAction.checkAction(className, nodeInfo, service);
        }
    }

    private boolean checkHui(String pakcageName) {
        return pakcageName.contains("com.cashtoutiao") || pakcageName.contains("com.bytedance");
    }

    private boolean checkShua(String pakcageName) {
        return pakcageName.contains("com.cashtoutiao") || pakcageName.contains("com.bytedance");
    }

}

package com.jeve.gestures.tool;

import android.os.Environment;

import com.jeve.gestures.action.ActionManager;
import com.jeve.gestures.content.AppContent;

import java.io.File;

public class ActionCheckTool {

    private ActionCheckTool() {
    }

    private static class Budler {
        private static final ActionCheckTool ACTION_CHECK_TOOL = new ActionCheckTool();
    }

    public static ActionCheckTool getInstance() {
        return Budler.ACTION_CHECK_TOOL;
    }

    private boolean shouldAction = false;

    public boolean setShouldAction() {
        shouldAction = !shouldAction;

        try {
            if (shouldAction) {
                //启动  修改日志名字
//                String filePath = Environment.getExternalStorageDirectory().getPath() + "/MakeMoney/";
//                String fileName = "locallog.txt";
//                File file = new File(filePath + fileName);
//                if (file.exists()) {
//                    file.delete();
//                }

                LocalLogTool.setFileName("locallog_" + LocalLogTool.getTime() + ".txt");
                //打印准备刷的APP名字
                for (AppContent appContent : ActionManager.getInstance().getAppList()) {
                    LocalLogTool.writeTxtToFile("勾选APP：" + appContent.getAppName() + "--" + appContent.getChangeTime());
                }
                LocalLogTool.writeTxtToFile("****************************************");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return shouldAction;
    }

    public boolean getShouldAction() {
        return shouldAction;
    }
}

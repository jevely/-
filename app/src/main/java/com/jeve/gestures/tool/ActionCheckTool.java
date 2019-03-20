package com.jeve.gestures.tool;

import android.os.Environment;

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
                //启动  删除日志
                String filePath = Environment.getExternalStorageDirectory().getPath() + "/MakeMoney/";
                String fileName = "locallog.txt";
                File file = new File(filePath + fileName);
                if (file.exists()) {
                    file.delete();
                }
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

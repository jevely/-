package com.jeve.gestures.tool;

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
        return shouldAction;
    }

    public boolean getShouldAction() {
        return shouldAction;
    }
}

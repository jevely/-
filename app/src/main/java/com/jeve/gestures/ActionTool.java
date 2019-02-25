package com.jeve.gestures;

public class ActionTool {

    private ActionTool() {
    }

    private static class Budler {
        private static final ActionTool actionTool = new ActionTool();
    }

    public static ActionTool getInstance() {
        return Budler.actionTool;
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

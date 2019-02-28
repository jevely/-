package com.jeve.gestures.action;

public abstract class TimeParent {

    public TimeParent() {
        new Thread(new ActionThr()).start();
    }

    private class ActionThr implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    doAction();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void doAction();

}

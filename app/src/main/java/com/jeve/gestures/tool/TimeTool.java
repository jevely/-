package com.jeve.gestures.tool;

import java.util.HashMap;
import java.util.Map;

public class TimeTool {

    private Map<String, Long> appActionTime;

    private TimeTool() {
        appActionTime = new HashMap<>();
    }

    private static final class Buidler {
        private static final TimeTool tool = new TimeTool();
    }

    public static TimeTool getInstance() {
        return Buidler.tool;
    }


}

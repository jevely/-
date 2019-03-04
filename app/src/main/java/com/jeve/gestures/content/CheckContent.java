package com.jeve.gestures.content;

import cn.bmob.v3.BmobObject;

/**
 * 检查
 */
public class CheckContent extends BmobObject {

    private String emei;
    private String code;
    private String time;

    public String getEmei() {
        return emei;
    }

    public void setEmei(String emei) {
        this.emei = emei;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

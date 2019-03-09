package com.jeve.gestures.content;

import cn.bmob.v3.BmobObject;

/**
 * 检查
 */
public class CheckContent extends BmobObject {

    private String emei;
    private String code;
    private Long time;
    private Long useTime;

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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getUseTime() {
        return useTime;
    }

    public void setUseTime(Long useTime) {
        this.useTime = useTime;
    }
}

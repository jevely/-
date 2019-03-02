package com.jeve.gestures.content;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

@Entity(nameInDb = "app_content")
public class AppContent {

    @Id(autoincrement = true)//设置自增长
    private Long id;
    private long changeTime;//多长时间换APP
    private long actionTime;//刷了多长时间
    @Index(unique = true)//设置唯一性
    private String packageName;//APP包名
    private String changePackageName;//跳转APP包名
    private String appName;//软件名字
    private boolean isCheck;
    private String openSelfPackageName;

    @Generated(hash = 1621300767)
    public AppContent(Long id, long changeTime, long actionTime, String packageName,
            String changePackageName, String appName, boolean isCheck,
            String openSelfPackageName) {
        this.id = id;
        this.changeTime = changeTime;
        this.actionTime = actionTime;
        this.packageName = packageName;
        this.changePackageName = changePackageName;
        this.appName = appName;
        this.isCheck = isCheck;
        this.openSelfPackageName = openSelfPackageName;
    }

    @Generated(hash = 763863524)
    public AppContent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(long changeTime) {
        this.changeTime = changeTime;
    }

    public long getActionTime() {
        return actionTime;
    }

    public void setActionTime(long actionTime) {
        this.actionTime = actionTime;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getChangePackageName() {
        return changePackageName;
    }

    public void setChangePackageName(String changePackageName) {
        this.changePackageName = changePackageName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean getIsCheck() {
        return this.isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public String getOpenSelfPackageName() {
        return openSelfPackageName;
    }

    public void setOpenSelfPackageName(String openSelfPackageName) {
        this.openSelfPackageName = openSelfPackageName;
    }
}

package com.jeve.gestures.service;

import android.text.TextUtils;

import com.jeve.gestures.content.CheckContent;
import com.jeve.gestures.inter.BmobChangeCallBack;
import com.jeve.gestures.inter.BmobCheckCallBack;
import com.jeve.gestures.inter.BmobGetCallBack;
import com.jeve.gestures.inter.BmobResultCallBack;
import com.jeve.gestures.tool.SharePreferenceTool;
import com.jeve.gestures.tool.Utils;

import java.util.List;

public class BmobManager implements BmobGetCallBack, BmobCheckCallBack, BmobChangeCallBack {

    private BmobResultCallBack bmobResultCallBack;

    private BmobManager() {
    }

    private static final class Buidler {
        private static final BmobManager manager = new BmobManager();
    }

    public static BmobManager getInstance() {
        return Buidler.manager;
    }

    //验证用户
    public void checkUser() {
        String code = SharePreferenceTool.getInstance().getString("code");
        if (TextUtils.isEmpty(code)) {
            if (bmobResultCallBack != null)
                bmobResultCallBack.checkUser(false, 1);
        } else {
            BmobTool.get(code, this);
        }
    }

    //根据code获取数据
    public void checkCode(String code) {
        if (TextUtils.isEmpty(code)) {
            if (bmobResultCallBack != null)
                bmobResultCallBack.checkCode(false, 1);
        } else {
            BmobTool.check(code, this);
        }
    }


    public void setBmobResultCallBack(BmobResultCallBack bmobResultCallBack) {
        this.bmobResultCallBack = bmobResultCallBack;
    }

    /**
     * 获取信息成功
     */
    @Override
    public void checkUserSuccess(List<CheckContent> object) {
        if (object.size() == 1) {
            CheckContent content = object.get(0);
            if (content.getTime() <= content.getUseTime()) {
                if (bmobResultCallBack != null)
                    bmobResultCallBack.checkUser(false, 2);
                return;
            }

            if (!TextUtils.equals(content.getEmei(), Utils.getIMEI())) {
                if (bmobResultCallBack != null)
                    bmobResultCallBack.checkUser(false, 3);
                return;
            }

            if (bmobResultCallBack != null)
                bmobResultCallBack.checkUser(true, 0);

        } else {
            if (bmobResultCallBack != null)
                bmobResultCallBack.checkUser(false, 4);
        }
    }

    /**
     * 获取信息失败
     */
    @Override
    public void checkUserError(String message) {
        if (bmobResultCallBack != null)
            bmobResultCallBack.checkUser(false, 5);
    }

    @Override
    public void checkCodeSuccess(List<CheckContent> object) {
        if (object.size() == 1) {
            CheckContent content = object.get(0);
            if (TextUtils.isEmpty(content.getEmei())) {
                String emei = Utils.getIMEI();
                if (!TextUtils.isEmpty(emei)) {
                    content.setEmei(emei);
                    content.setUseTime(0L);
                    //更新数据
                    BmobTool.change(content, this);
                    //保存code
                    SharePreferenceTool.getInstance().setString("code", content.getCode());
                }
            } else {
                if (TextUtils.equals(content.getEmei(), Utils.getIMEI())) {
                    //可以使用
                    if (bmobResultCallBack != null)
                        bmobResultCallBack.checkCode(true, 0);
                    //保存code
                    SharePreferenceTool.getInstance().setString("code", content.getCode());
                    return;
                }
                //别人已经用过
                if (bmobResultCallBack != null)
                    bmobResultCallBack.checkCode(false, 2);
            }
        } else {
            //错误 联系管理员
            if (bmobResultCallBack != null)
                bmobResultCallBack.checkCode(false, 3);
        }
    }

    @Override
    public void checkCodeError(String message) {
        if (bmobResultCallBack != null)
            bmobResultCallBack.checkCode(false, 4);
    }

    @Override
    public void changeUserSuccess() {
        if (bmobResultCallBack != null)
            bmobResultCallBack.checkCode(true, 0);
    }

    @Override
    public void changeUserError(String message) {
        if (bmobResultCallBack != null)
            bmobResultCallBack.checkCode(false, 4);
    }

}

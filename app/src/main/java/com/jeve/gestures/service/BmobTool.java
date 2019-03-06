package com.jeve.gestures.service;

import com.jeve.gestures.content.CheckContent;
import com.jeve.gestures.inter.BmobChangeCallBack;
import com.jeve.gestures.inter.BmobCheckCallBack;
import com.jeve.gestures.inter.BmobResultCallBack;
import com.jeve.gestures.inter.BmobGetCallBack;
import com.jeve.gestures.tool.Logger;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class BmobTool {

    /**
     * 保存
     */
    public static void save(CheckContent checkContent) {
        checkContent.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                Logger.d("保存成功");
            }
        });
    }

    /**
     * 修改
     */
    public static void change(CheckContent checkContent, final BmobChangeCallBack callBack) {
        checkContent.update(checkContent.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    callBack.changeUserSuccess();
                } else {
                    callBack.changeUserError(e.getMessage());
                }
            }
        });
    }

    /**
     * 获取
     */
    public static void get(String code, final BmobGetCallBack callBack) {
        BmobQuery<CheckContent> categoryBmobQuery = new BmobQuery<>();
        categoryBmobQuery.addWhereEqualTo("code", code);
        categoryBmobQuery.findObjects(new FindListener<CheckContent>() {
            @Override
            public void done(List<CheckContent> object, BmobException e) {
                if (e == null) {
                    callBack.checkUserSuccess(object);
                } else {
                    callBack.checkUserError(e.getMessage());
                }
            }
        });
    }

    /**
     * 检查是否有code对应的数据
     */
    public static void check(String code, final BmobCheckCallBack callBack) {
        BmobQuery<CheckContent> categoryBmobQuery = new BmobQuery<>();
        categoryBmobQuery.addWhereEqualTo("code", code);
        categoryBmobQuery.findObjects(new FindListener<CheckContent>() {
            @Override
            public void done(List<CheckContent> object, BmobException e) {
                if (e == null) {
                    callBack.checkCodeSuccess(object);
                } else {
                    callBack.checkCodeError(e.getMessage());
                }
            }
        });
    }

    /**
     * 获取所有
     */
    public static void getAll() {
        BmobQuery<CheckContent> bmobQuery = new BmobQuery<>();
        bmobQuery.findObjects(new FindListener<CheckContent>() {
            @Override
            public void done(List<CheckContent> categories, BmobException e) {
                if (e == null) {

                } else {

                }
            }
        });
    }

}

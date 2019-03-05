package com.jeve.gestures.service;

import com.jeve.gestures.content.CheckContent;
import com.jeve.gestures.inter.BmobCallBack;
import com.jeve.gestures.inter.BmobCallBack2;
import com.jeve.gestures.tool.Logger;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class BmobTool {

    public static void save(CheckContent checkContent) {
        checkContent.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                Logger.d("保存成功");
            }
        });
    }

    public static void change(CheckContent checkContent, final BmobCallBack2 callBack2) {
        checkContent.update(checkContent.getObjectId(), new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if (e == null) {
                    callBack2.result();
                } else {
                    callBack2.error();
                }
            }
        });
    }

    /**
     * 获取
     */
    public static void get(String code, final BmobCallBack callBack) {
        BmobQuery<CheckContent> categoryBmobQuery = new BmobQuery<>();
        categoryBmobQuery.addWhereEqualTo("code", code);
        categoryBmobQuery.findObjects(new FindListener<CheckContent>() {
            @Override
            public void done(List<CheckContent> object, BmobException e) {
                if (e == null) {
                    callBack.result(object);
                } else {
                    callBack.error();
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

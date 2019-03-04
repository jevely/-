package com.jeve.gestures.service;

import com.jeve.gestures.content.CheckContent;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class BmobTool {

    public static void change(CheckContent checkContent) {
        checkContent.update(checkContent.getObjectId(), new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if (e == null) {

                } else {

                }
            }
        });
    }

    /**
     * 获取
     */
    public static void get(String code) {
        BmobQuery<CheckContent> categoryBmobQuery = new BmobQuery<>();
        categoryBmobQuery.addWhereEqualTo("code", code);
        categoryBmobQuery.findObjects(new FindListener<CheckContent>() {
            @Override
            public void done(List<CheckContent> object, BmobException e) {
                if (e == null) {

                } else {

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

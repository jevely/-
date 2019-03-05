package com.jeve.gestures.tool;

import android.content.Context;
import android.content.SharedPreferences;

import com.jeve.gestures.MyApplication;

public class SharePreferenceTool {

    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;

    private SharePreferenceTool() {
        sharedPreferences = MyApplication.getContext().getSharedPreferences("makemoney", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();//获取编辑器
    }

    private static final class Builder {
        private static final SharePreferenceTool tool = new SharePreferenceTool();
    }

    public static SharePreferenceTool getInstance() {
        return Builder.tool;
    }

    public void setString(String key, String content) {
        editor.putString(key, content);
        editor.commit();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }


}

package com.jeve.gestures.inter;

import com.jeve.gestures.content.CheckContent;

import java.util.List;

/**
 * 根据code获取数据回调
 */
public interface BmobGetCallBack {

    void checkUserSuccess(List<CheckContent> object);

    void checkUserError(String message);

}

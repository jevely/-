package com.jeve.gestures.inter;

import com.jeve.gestures.content.CheckContent;

import java.util.List;

/**
 * 检查用户回调
 */
public interface BmobCheckCallBack {

    void checkCodeSuccess(List<CheckContent> object);

    void checkCodeError(String message);

}

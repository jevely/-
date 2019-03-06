package com.jeve.gestures.inter;

import com.jeve.gestures.content.CheckContent;

import java.util.List;

/**
 * 网络服务接口
 */
public interface BmobCheckCallBack {

    void checkCodeSuccess(List<CheckContent> object);

    void checkCodeError(String message);

}

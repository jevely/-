package com.jeve.gestures.inter;

import com.jeve.gestures.content.CheckContent;

import java.util.List;

public interface BmobCallBack {

    void result(List<CheckContent> checkContent);

    void error();

}

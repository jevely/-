package com.jeve.gestures.inter;

/**
 * 检查用户等接口
 */
public interface BmobResultCallBack {

    //1 code为空   2 code时间没有了  3 code错误  4 其他错误  5 请求没成功
    void checkUser(boolean isUser, int code);

    //1 code为空  2 已经用过了  3 code异常  4 网络异常
    void checkCode(boolean success, int code);

}

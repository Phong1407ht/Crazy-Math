package com.techja.crazymath.view;

public interface OnMainCallBack {
    void showFragment(String tag, Object data, boolean isBack);
    void backToPrevious();
}

package com.example.yue2018a.base;

import android.content.Context;

/**
 * date:2018/12/21
 * author:mxy(M)
 * function:
 */

public abstract class BasePresenter<V extends IView> {

    protected V iView;

    public BasePresenter() {
        provideModel();
    }

    protected abstract void provideModel();

    public void attach(V iView) {
        this.iView = iView;
    }

    public void detach() {
        if (iView != null) {
            iView = null;
        }
    }

    public Context getContext() {
        if (iView != null) {
            return iView.getContext();
        }
        return null;
    }
}

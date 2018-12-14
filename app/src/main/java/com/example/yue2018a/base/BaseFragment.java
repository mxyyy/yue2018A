package com.example.yue2018a.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date:2018/12/21
 * author:mxy(M)
 * function:
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IView {
    protected P presenter;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(provideLayoutId(), container, false);
        bind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = providePresenter();
        attachIView();
        initView();
        setListener();
        initData();
    }

    private void attachIView() {
        if (presenter != null) {
            presenter.attach(this);
        }
    }

    protected abstract void initData();

    protected void setListener() {

    }

    protected void initView() {

    }

    protected abstract P providePresenter();

    protected abstract int provideLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
        if (presenter != null) {
            presenter = null;
        }
    }
}

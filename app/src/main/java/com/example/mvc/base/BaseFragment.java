package com.example.mvc.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Description:
 */

public abstract class BaseFragment extends Fragment {

    public  Context f_context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        f_context = getActivity();
        initVariable();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = setLayoutId(inflater, container, savedInstanceState);
        init();
        initListener();
        return fragmentView;
    }

    protected  abstract View setLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    protected  void initVariable(){}
    protected void init(){}
    protected void initListener(){}

}

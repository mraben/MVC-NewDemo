package com.example.mvc.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvc.R;
import com.example.mvc.base.BaseFragment;

/**
 *
 */
public class LikeFragment extends BaseFragment {

    private View likeInflate;

    @Override
    protected View setLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        likeInflate = View.inflate(getContext(),R.layout.likelayout, null);
        return likeInflate;
    }
}

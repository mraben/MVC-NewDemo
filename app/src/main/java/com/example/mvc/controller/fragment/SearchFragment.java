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
public class SearchFragment extends BaseFragment {
    private View searchInflate;

    @Override
    protected View setLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        searchInflate = View.inflate(getContext(),R.layout.searchlayout, null);
        return searchInflate;
    }
}

package com.example.mvc.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvc.R;
import com.example.mvc.base.BaseFragment;
import com.example.mvc.ui.fragment.controller.HomeController;
import com.example.mvc.ui.fragment.model.HomeModel;
import com.example.mvc.utils.ToastUtil;

/**
 * Description:
 */
public class HomeFragment extends BaseFragment implements HomeController.OnCallBcakListener {

    private HomeModel hlmp;
    private  View homeInflate;
    private TextView tvToast;

    @Override
    protected View setLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        hlmp = new HomeModel(this);
        homeInflate = View.inflate(getContext(), R.layout.homelayout, null);
        return homeInflate;
    }

    @Override
    protected void init() {
        super.init();
        findView();
        hlmp.getData(0);
    }

    private void findView(){
        tvToast=  homeInflate.findViewById(R.id.tv_toast);
    }

    @Override
    public void onSuccess(String s) {
        ToastUtil.showShort(getContext(),s);
    }

    @Override
    public void onFail(String e) {
        ToastUtil.showShort(getContext(),e);
    }
}

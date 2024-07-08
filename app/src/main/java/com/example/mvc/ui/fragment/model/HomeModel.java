package com.example.mvc.ui.fragment.model;


import com.example.mvc.base.Model;
import com.example.mvc.base.RxSchedelers;
import com.example.mvc.ui.fragment.controller.HomeController;

import okhttp3.ResponseBody;

/**
 * Description:
 */
public class HomeModel implements HomeController {

    private final OnCallBcakListener listener;

    public HomeModel(OnCallBcakListener listener) {
        this.listener = listener;
    }

    @Override
    public void getData(int id) {
        Model m = Model.getInstance();
        m.getApi().getempty().subscribe(new RxSchedelers<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
                super.onNext(responseBody);
                listener.onSuccess("成功");
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                listener.onFail("失败");
            }
        });
    }

}




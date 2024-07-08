package com.example.mvc.controller.model;


import com.example.mvc.base.Model;
import com.example.mvc.base.RxSchedelers;
import com.example.mvc.iview.OnHomeListener;

import okhttp3.ResponseBody;

/**
 * Description:
 */
public class HomeModellmpl implements HomeModel {
    @Override
    public void getData(int id, OnHomeListener listener) {
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




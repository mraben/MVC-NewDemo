package com.example.mvc.base;

import com.example.mvc.api.Api;

/**
 * Description: 处理请求
 */
public class Model {
    private static Model model;

    private Api api = BaseRetrofit.getInstance().getObservable(new ApiIn());

    /**
     * @return 返回Model实例
     */
    public static Model getInstance() {

        if (model == null) {
            synchronized (Model.class) {
                if (model == null) {
                    model = new Model();
                }
            }
        }
        return model;
    }

    public Api getApi() {
        return api;
    }

}
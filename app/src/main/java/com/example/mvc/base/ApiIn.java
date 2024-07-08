package com.example.mvc.base;


import com.example.mvc.api.Api;

import retrofit2.Retrofit;

/**
 * Description:
 */
public class ApiIn extends BaseApi {

    @Override
    public Api getObservable(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }
}

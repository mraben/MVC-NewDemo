package com.example.mvc.ui.fragment.controller;


/**
 * Description:
 */
public interface HomeController {

    interface OnCallBcakListener {
        void onSuccess(String s);

        void onFail(String e);
    }

    void getData(int id);

}

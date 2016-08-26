package com.example.murphy.coolweather.util;

/**
 * Created by Murphy on 16/8/19.
 */
public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}

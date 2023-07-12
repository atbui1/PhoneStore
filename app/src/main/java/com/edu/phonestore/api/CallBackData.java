package com.edu.phonestore.api;

public interface CallBackData<T> {
    void onSuccess(T t);
    void onFail(String msgFail);
}

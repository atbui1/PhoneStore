package com.edu.phonestore.view;

public interface CallBackView<T> {
    void onSuccess(T t);
    void onFail(String msgFail);
}

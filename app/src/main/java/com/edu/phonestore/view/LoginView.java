package com.edu.phonestore.view;

import com.edu.phonestore.payload.response.LoginResponse;
import com.edu.phonestore.room.entity.User;

public interface LoginView {
    void loginSuccess(User user);
    void loginFail(String msg);
}

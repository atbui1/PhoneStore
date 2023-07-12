package com.edu.phonestore.view;

import com.edu.phonestore.room.entity.User;

public interface LogoutView {
    void logoutSuccess();
    void logoutFail(String msg);
}

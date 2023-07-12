package com.edu.phonestore.view;

import com.edu.phonestore.room.entity.User;

public interface RememberView {
    void rememberSuccess(User user);
    void rememberFail(String msg);
}

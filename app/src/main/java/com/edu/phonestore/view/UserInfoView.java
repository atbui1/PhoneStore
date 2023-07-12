package com.edu.phonestore.view;

import com.edu.phonestore.room.entity.User;

public interface UserInfoView {
    void userInfoSuccess(User user);
    void userInfoFailed(String msg);
}

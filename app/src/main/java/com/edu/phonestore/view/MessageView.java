package com.edu.phonestore.view;

import com.edu.phonestore.room.entity.User;

import java.util.List;

public interface MessageView {
    void messageSuccess(List<User> users);
    void messageFailed(String msg);
}

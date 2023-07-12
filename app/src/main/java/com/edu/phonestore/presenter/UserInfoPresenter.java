package com.edu.phonestore.presenter;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.edu.phonestore.room.entity.User;
import com.edu.phonestore.room.management.UserManagement;
import com.edu.phonestore.view.RememberView;
import com.edu.phonestore.view.UserInfoView;

public class UserInfoPresenter {
    private UserInfoView userInfoView;
    private Context context;
    private UserManagement userManagement;
    private Application application;

    public UserInfoPresenter(Application application, Context context, UserInfoView userInfoView) {
        this.application = application;
        this.context = context;
        this.userInfoView = userInfoView;
        userManagement = new UserManagement(application);
    }

    public void userInfo() {
        userManagement.userInfoFromDB(new UserManagement.OnDataCallBackUser() {
            @Override
            public void onDataSuccess(User user) {
                System.out.println("userInfo presenter ssssssssssssssssssssssssss: " + user);
                Toast.makeText(context, "userInfo presenter success", Toast.LENGTH_SHORT).show();
                userInfoView.userInfoSuccess(user);
            }

            @Override
            public void onDataFail() {
                System.out.println("userInfo presenter ffffffffffffffffffffff");
                Toast.makeText(context, "userInfo presenter failed", Toast.LENGTH_SHORT).show();
                userInfoView.userInfoFailed("Khong ton tai user, vui long kiem tra lai");
            }
        });
    }
}

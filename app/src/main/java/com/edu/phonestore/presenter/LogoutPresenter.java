package com.edu.phonestore.presenter;

import android.app.Application;
import android.content.Context;

import com.edu.phonestore.api.CallBackData;
import com.edu.phonestore.phone.PhoneRepository;
import com.edu.phonestore.phone.PhoneRepositoryImp;
import com.edu.phonestore.room.entity.User;
import com.edu.phonestore.room.management.UserManagement;
import com.edu.phonestore.utils.AppDialog;
import com.edu.phonestore.view.LoginView;
import com.edu.phonestore.view.LogoutView;

public class LogoutPresenter {
    private LogoutView logoutView;
    private Context context;
    private UserManagement userManagement;
    private Application application;

    public LogoutPresenter(Application application, Context context, LogoutView logoutView) {
        this.application = application;
        this.context = context;
        this.logoutView = logoutView;
        userManagement = new UserManagement(application);
    }

    public void logout() {
        userManagement.deleteUserToDB(new UserManagement.OnDataCallBackUser() {
            @Override
            public void onDataSuccess(User user) {
                System.out.println("11 logout 1111111111111111111111111111111111111111111111111111");
                System.out.println("logout: " + user);
                System.out.println("22 logout 1111111111111111111111111111111111111111111111111111");
                logoutView.logoutSuccess();
            }

            @Override
            public void onDataFail() {
                logoutView.logoutFail("");
            }
        });
    }

    
}

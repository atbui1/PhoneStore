package com.edu.phonestore.presenter;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;

import com.edu.phonestore.api.CallBackData;
import com.edu.phonestore.payload.response.LoginResponse;
import com.edu.phonestore.phone.PhoneRepository;
import com.edu.phonestore.phone.PhoneRepositoryImp;
import com.edu.phonestore.room.entity.User;
import com.edu.phonestore.room.management.UserManagement;
import com.edu.phonestore.utils.AppDialog;
import com.edu.phonestore.view.LoginView;

public class LoginPresenter {
    private PhoneRepository phoneRepository;
    private LoginView loginView;
    private Context context;
    private UserManagement userManagement;
    private Application application;
    private AppDialog appDialog;

    public LoginPresenter(Application application, Context context, LoginView loginView) {
        this.application = application;
        this.context = context;
        this.loginView = loginView;
        phoneRepository = new PhoneRepositoryImp();
        userManagement = new UserManagement(application);
        appDialog = new AppDialog(context);
    }

    public void login(final String username, final String password) {
        phoneRepository.loginApp(context, username, password, new CallBackData<User>() {
            @Override
            public void onSuccess(User user) {
//                loginView.loginSuccess(loginResponse);
                if (user != null) {
                    System.out.println("11 LLLLLLLLLLLLLLLLLLLLL--------------TTTTTTTTTTTTTTTTTTTTTTTTTTT");
                    System.out.println("ll user: " + user);
                    System.out.println("22 LLLLLLLLLLLLLLLLLLLLL--------------TTTTTTTTTTTTTTTTTTTTTTTTTTT");
                    addUserToRoom(user);
                } else {
                    System.out.println("LLLLLLLLLLLLLLLLLLLLLLLL---------------FFFFFFFFFFFFFFFFFFFFFFFFFF");
                    loginView.loginFail("luu thong in nguoi dung that bai");
                }
            }

            @Override
            public void onFail(String msgFail) {
//                msgFail = "Sai số điện thoại hoặc mật khẩu";
//                String txtOption = "Close";
//                loginView.loginFail(msgFail);
                showDialogError();

            }
        });
    }

    private void addUserToRoom(User user) {
        System.out.println("login ****************************************** user: " + user);
        userManagement.addUserToDB(user, new UserManagement.OnDataCallBackUser() {
            @Override
            public void onDataSuccess(User user) {
                System.out.println("11 AAAAAAAAAAAAAAAAAAAAAAAAAA11111111111111111111SSSSSSSSSSSSSSSSSSSSSSSSSSSS");
                System.out.println("aa user: " + user);
                System.out.println("22 AAAAAAAAAAAAAAAAAAAAAAAAAA11111111111111111111SSSSSSSSSSSSSSSSSSSSSSSSSSSS");
                loginView.loginSuccess(user);
            }

            @Override
            public void onDataFail() {
                loginView.loginFail("luu thong tin nguoi dung fffffffffff");
            }
        });
    }

    private void showDialogError() {
        String msgFail = "Sai số điện thoại hoặc mật khẩu";
        String txtOption = "Close";
        appDialog.showDialogOneOption(msgFail, txtOption, "#ff0000", "#6ad79e");
    }
}

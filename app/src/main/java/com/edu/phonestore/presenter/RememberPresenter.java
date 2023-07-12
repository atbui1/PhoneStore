package com.edu.phonestore.presenter;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.edu.phonestore.api.CallBackData;
import com.edu.phonestore.phone.PhoneRepository;
import com.edu.phonestore.phone.PhoneRepositoryImp;
import com.edu.phonestore.room.entity.User;
import com.edu.phonestore.room.management.UserManagement;
import com.edu.phonestore.utils.AppDialog;
import com.edu.phonestore.view.LoginView;
import com.edu.phonestore.view.RememberView;

public class RememberPresenter {
    private RememberView rememberView;
    private Context context;
    private UserManagement userManagement;
    private Application application;

    public RememberPresenter(Application application, Context context, RememberView rememberView) {
        this.application = application;
        this.context = context;
        this.rememberView = rememberView;
        userManagement = new UserManagement(application);
    }

    public void rememberUser() {
        userManagement.checkExistUserToDB(new UserManagement.OnDataCallBackUser() {
            @Override
            public void onDataSuccess(User user) {
                System.out.println("remember ssssssssssssssssssssssssss: " + user);
                Toast.makeText(context, "lan 2 co account", Toast.LENGTH_SHORT).show();
                rememberView.rememberSuccess(user);
            }

            @Override
            public void onDataFail() {
                System.out.println("remember ffffffffffffffffffffff");
                rememberView.rememberFail("");
            }
        });
    }
}

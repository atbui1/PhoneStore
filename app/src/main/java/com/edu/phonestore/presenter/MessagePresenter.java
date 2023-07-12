package com.edu.phonestore.presenter;

import android.app.Application;
import android.content.Context;

import com.edu.phonestore.api.CallBackData;
import com.edu.phonestore.phone.PhoneRepository;
import com.edu.phonestore.phone.PhoneRepositoryImp;
import com.edu.phonestore.room.entity.User;
import com.edu.phonestore.utils.AppDialog;
import com.edu.phonestore.view.MessageView;

import java.util.List;

public class MessagePresenter {
    private PhoneRepository phoneRepository;
    private MessageView messageView;
    private Context context;
    private Application application;
    private AppDialog appDialog;

    public MessagePresenter(Application application, Context context, MessageView messageView) {
        this.application = application;
        this.context = context;
        this.messageView = messageView;
        phoneRepository = new PhoneRepositoryImp();
        appDialog = new AppDialog(context);
    }

    public void getMessages() {
        phoneRepository.getMessages(context, new CallBackData<List<User>>() {
            @Override
            public void onSuccess(List<User> users) {
                System.out.println("call api message success - size: " + users.size());
                messageView.messageSuccess(users);
            }

            @Override
            public void onFail(String msgFail) {
                String txtOption = "Dong";
                appDialog.showDialogOneOption(msgFail, txtOption, "#ff0000", "#6ad79e");
            }
        });
    }

    private void showDialogError() {
        String msgFail = "Sai số điện thoại hoặc mật khẩu";
        String txtOption = "Close";
        appDialog.showDialogOneOption(msgFail, txtOption, "#ff0000", "#6ad79e");
    }
}

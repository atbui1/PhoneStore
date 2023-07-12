package com.edu.phonestore.presenter;

import android.app.Application;
import android.content.Context;

import com.edu.phonestore.api.CallBackData;
import com.edu.phonestore.model.Product;
import com.edu.phonestore.phone.PhoneRepository;
import com.edu.phonestore.phone.PhoneRepositoryImp;
import com.edu.phonestore.room.entity.User;
import com.edu.phonestore.room.management.UserManagement;
import com.edu.phonestore.utils.AppDialog;
import com.edu.phonestore.view.LoginView;
import com.edu.phonestore.view.ProductView;

import java.util.List;

public class ProductPresenter {
    private PhoneRepository phoneRepository;
    private ProductView productView;
    private Context context;
    private UserManagement userManagement;
    private Application application;
    private AppDialog appDialog;

    public ProductPresenter(Application application, Context context, ProductView productView) {
        this.application = application;
        this.context = context;
        this.productView = productView;
        phoneRepository = new PhoneRepositoryImp();
        userManagement = new UserManagement(application);
        appDialog = new AppDialog(context);
    }

    public void getProducts() {
        phoneRepository.getProducts(context, new CallBackData<List<Product>>() {
            @Override
            public void onSuccess(List<Product> products) {
                productView.productSuccess(products);
            }

            @Override
            public void onFail(String msgFail) {
//                productView.productFailed(msgFail);
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

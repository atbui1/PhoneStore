package com.edu.phonestore.phone;

import android.content.Context;

import com.edu.phonestore.api.CallBackData;
import com.edu.phonestore.model.Product;
import com.edu.phonestore.payload.response.LoginResponse;
import com.edu.phonestore.room.entity.User;

import java.util.List;

public interface PhoneRepository {
    void loginApp(Context context, String username, String password, CallBackData<User> callBackData);

    void getMessages(Context context, CallBackData<List<User>> callBackData);

    /** product */
    void getProducts(Context context, CallBackData<List<Product>> callBackData);
}

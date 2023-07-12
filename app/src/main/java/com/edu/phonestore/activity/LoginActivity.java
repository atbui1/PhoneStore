package com.edu.phonestore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Toast;

import com.edu.phonestore.R;
import com.edu.phonestore.databinding.ActivityLoginBinding;
import com.edu.phonestore.presenter.LoginPresenter;
import com.edu.phonestore.room.entity.User;
import com.edu.phonestore.view.LoginView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginView {

    private ActivityLoginBinding mBinding;
    private boolean isShowPassword = false;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
//        setContentView(R.layout.activity_login);

        initialView();
        initialData();
    }

    private void initialView() {
        mBinding.lnlShowHide.setOnClickListener(this);
        mBinding.btnLogin.setOnClickListener(this);
    }

    private void initialData() {
        loginPresenter = new LoginPresenter(getApplication(), this, this);
    }

    private void showHidePass() {
        if (isShowPassword) {
            mBinding.edtPassword.setTransformationMethod(new PasswordTransformationMethod());
            mBinding.imgShowHide.setImageDrawable(getResources().getDrawable(R.drawable.icon_eye_show));
            isShowPassword = false;
        } else {
            mBinding.edtPassword.setTransformationMethod(null);
            mBinding.imgShowHide.setImageDrawable(getResources().getDrawable(R.drawable.icon_eye_hide));
            isShowPassword = true;
        }
    }

    private void checkLogin() {
        String username = mBinding.edtUsername.getText().toString().trim();
        String password = mBinding.edtPassword.getText().toString().trim();
        System.out.println("INFO: username " + username);
        System.out.println("INFO: password " + password);
        loginPresenter.login(username, password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lnl_show_hide:
                showHidePass();
                break;
            case R.id.btn_login:
                checkLogin();
                break;
        }
    }


    @Override
    public void loginSuccess(User user) {
        System.out.println("login activity 111111111111111111111111: " + user);
        Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginFail(String msg) {
        Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show();
        System.out.println("login activity ffffffffffffffffffffffffffff: " + msg);
    }
}
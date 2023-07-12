package com.edu.phonestore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.edu.phonestore.R;
import com.edu.phonestore.databinding.ActivitySplashBinding;
import com.edu.phonestore.presenter.RememberPresenter;
import com.edu.phonestore.room.entity.User;
import com.edu.phonestore.view.RememberView;

public class SplashActivity extends AppCompatActivity implements RememberView {

    private ActivitySplashBinding mBinding;
    private RememberPresenter rememberPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
//        setContentView(R.layout.activity_splash);
        setContentView(view);

        initialData();
        startAnimation();

    }
    private void initialData() {
        rememberPresenter = new RememberPresenter(getApplication(), this, this);
        rememberPresenter.rememberUser();
//        gotoLogin();
    }

    private void gotoLogin() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
    private void gotoMain() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    private void startAnimation() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mBinding.imgLogo.animate()
                        .rotationBy(360)
                        .withEndAction(this)
                        .setDuration(3000)
                        .setInterpolator(new LinearInterpolator()).start();
            }
        };
        mBinding.imgLogo.animate()
                .rotationBy(360)
                .withEndAction(runnable)
                .setDuration(3000)
                .setInterpolator(new LinearInterpolator()).start();
    }

    @Override
    public void rememberSuccess(User user) {
        Toast.makeText(this, "exist user", Toast.LENGTH_SHORT).show();
        gotoMain();
    }

    @Override
    public void rememberFail(String msg) {
        Toast.makeText(this, "user null", Toast.LENGTH_SHORT).show();
        gotoLogin();
    }
}
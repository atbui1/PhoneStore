package com.edu.phonestore.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.edu.phonestore.R;
import com.edu.phonestore.animation.DepthPageTransformer;
import com.edu.phonestore.databinding.ActivityMainBinding;
import com.edu.phonestore.paper.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        mView = mBinding.getRoot();
        setContentView(mView);
//        setContentView(R.layout.activity_main);

        initialView();
        initialData();

    }

    private void initialView() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        mBinding.viewPaper.setAdapter(viewPagerAdapter);
//        animation view paper
        mBinding.viewPaper.setPageTransformer(new DepthPageTransformer());
//        xu ly su kien vuot phai trai chuyen man hinh
        mBinding.viewPaper.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                         mBinding.bottomNavigation.getMenu().findItem(R.id.nav_home).setChecked(true);
                        break;
                    case 1:
                        mBinding.bottomNavigation.getMenu().findItem(R.id.nav_message).setChecked(true);
                        break;
                    case 2:
                        mBinding.bottomNavigation.getMenu().findItem(R.id.nav_notification).setChecked(true);
                        break;
                    case 3:
                        mBinding.bottomNavigation.getMenu().findItem(R.id.nav_cart).setChecked(true);
                        break;
                    case 4:
                        mBinding.bottomNavigation.getMenu().findItem(R.id.nav_personal).setChecked(true);
                        break;
                }
            }
        });

//        xu ly su kien click vao item bottom navigation
        mBinding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        mBinding.viewPaper.setCurrentItem(0);
                        break;
                    case R.id.nav_message:
                        mBinding.viewPaper.setCurrentItem(1);
                        break;
                    case R.id.nav_notification:
                        mBinding.viewPaper.setCurrentItem(2);
                        break;
                    case R.id.nav_cart:
                        mBinding.viewPaper.setCurrentItem(3);
                        break;
                    case R.id.nav_personal:
                        mBinding.viewPaper.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });

//        mBinding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.nav_home:
//                        mBinding.viewPaper.setCurrentItem(0);
//                        break;
//                    case R.id.nav_notification:
//                        mBinding.viewPaper.setCurrentItem(1);
//                        break;
//                    case R.id.nav_cart:
//                        mBinding.viewPaper.setCurrentItem(2);
//                        break;
//                    case R.id.nav_personal:
//                        mBinding.viewPaper.setCurrentItem(3);
//                        break;
//                }
//                return true;
//            }
//        });
    }

    private void initialData() {

    }
}
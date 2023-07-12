package com.edu.phonestore.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.edu.phonestore.R;
import com.edu.phonestore.activity.LoginActivity;
import com.edu.phonestore.databinding.FragmentPersonBinding;
import com.edu.phonestore.presenter.LogoutPresenter;
import com.edu.phonestore.presenter.UserInfoPresenter;
import com.edu.phonestore.room.entity.User;
import com.edu.phonestore.view.LogoutView;
import com.edu.phonestore.view.UserInfoView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonFragment extends Fragment implements View.OnClickListener, UserInfoView, LogoutView {

    private FragmentPersonBinding mBinding;
    private View mView;
    private LogoutPresenter logoutPresenter;
    private UserInfoPresenter userInfoPresenter;

    public PersonFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentPersonBinding.inflate(inflater, container, false);
        mView = mBinding.getRoot();
//        return inflater.inflate(R.layout.fragment_person, container, false);

        initialView();

        return mView;
    }

    private void initialView() {
        mBinding.txtUsername.setText("nguyen van a");
        mBinding.txtUserEmail.setText("Khach hang bac");
        mBinding.txtLogout.setOnClickListener(this);

        userInfoPresenter = new UserInfoPresenter(getActivity().getApplication(), getActivity(), this);
        userInfoPresenter.userInfo();
        logoutPresenter = new LogoutPresenter(getActivity().getApplication(), getActivity(), this);

    }

    private void logout() {
        logoutPresenter.logout();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_logout:
                logout();
                break;
        }
    }

    @Override
    public void userInfoSuccess(User user) {
        if (user != null) {
            mBinding.txtUsername.setText(user.getUsername());
            mBinding.txtUserEmail.setText(user.getEmail());
        }
    }

    @Override
    public void userInfoFailed(String msg) {
        Toast.makeText(getContext(), "not found user from DB room", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void logoutSuccess() {
        Toast.makeText(getContext(), "logout success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
//        intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void logoutFail(String msg) {
        Toast.makeText(getContext(), "logout failed", Toast.LENGTH_SHORT).show();
    }
}
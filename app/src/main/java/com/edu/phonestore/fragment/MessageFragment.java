package com.edu.phonestore.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.edu.phonestore.activity.ChatActivity;
import com.edu.phonestore.adapter.MessageAdapter;
import com.edu.phonestore.click.IClickMessage;
import com.edu.phonestore.databinding.FragmentMessageBinding;
import com.edu.phonestore.presenter.MessagePresenter;
import com.edu.phonestore.room.entity.User;
import com.edu.phonestore.view.MessageView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageFragment extends Fragment implements MessageView, IClickMessage {

    public static final String KEY_USER = "KEY_USER";

    private FragmentMessageBinding mBinding;
    private View mView;
    private MessagePresenter messagePresenter;
    private MessageAdapter messageAdapter;
    private List<User> users;



    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_notification, container, false);
        mBinding = FragmentMessageBinding.inflate(inflater, container, false);
        mView = mBinding.getRoot();

        initialView();
        initialData();

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getMessages();
    }

    private void initialView() {
        System.out.println("message initial view 111111111111111111111111111111111111111111111111111");
//        Toast.makeText(getContext(), "message fragment", Toast.LENGTH_SHORT).show();
        messagePresenter = new MessagePresenter(getActivity().getApplication(), getActivity(), this);
        mBinding.recyclerMessage.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.recyclerMessage.setLayoutManager(layoutManager);

        getMessages();
    }

    private void initialData() {
    }
    public void getMessages() {
//        Toast.makeText(getContext(), "call getUsers == message", Toast.LENGTH_SHORT).show();
        messagePresenter.getMessages();
    }
    private void updateUI() {
        System.out.println("message updateUI 22222222222222222222222222222222222222222222222222222222");
        if (messageAdapter == null) {
            messageAdapter = new MessageAdapter(users, getContext().getApplicationContext(), this);
            mBinding.recyclerMessage.setAdapter(messageAdapter);
        } else {
            messageAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void clickMessage(User user) {
//        Toast.makeText(getContext(), "click avatar", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), ChatActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_USER, user);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    @Override
    public void messageSuccess(List<User> userList) {
//        Toast.makeText(getContext(), "get users == message success", Toast.LENGTH_SHORT).show();
        users = userList;
        updateUI();

    }

    @Override
    public void messageFailed(String msg) {

    }
}
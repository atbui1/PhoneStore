package com.edu.phonestore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.edu.phonestore.R;
import com.edu.phonestore.adapter.ChatAdapter;
import com.edu.phonestore.click.IClickChat;
import com.edu.phonestore.databinding.ActivityChatBinding;
import com.edu.phonestore.databinding.ActivityMainBinding;
import com.edu.phonestore.model.Chat;
import com.edu.phonestore.room.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements IClickChat {
    public static final String KEY_USER = "KEY_USER";
    private ActivityChatBinding mBinding;
    private View mView;
    private List<Chat> chats;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chat);
        mBinding = ActivityChatBinding.inflate(getLayoutInflater());
        mView = mBinding.getRoot();
        setContentView(mView);
        
        initialView();
        initialData();
    }

    private void initialView() {
        getDataFromMessages();

        mBinding.recyclerChat.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.recyclerChat.setLayoutManager(layoutManager);

        chats = new ArrayList<>();
        chatAdapter = new ChatAdapter();
        chatAdapter.setData(chats);
        mBinding.recyclerChat.setAdapter(chatAdapter);

        mBinding.btnSendMessage.setOnClickListener(v -> sendDataMessage());
        mBinding.edtEnterMessage.setOnClickListener(v -> checkKeyboard());
    }

    private void initialData() {

    }

    private void getDataFromMessages() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            User user = (User) bundle.getSerializable(KEY_USER);
            if (user != null) {
                String userName = user.getUsername();
                Toast.makeText(this, "chat with user's name: " + userName, Toast.LENGTH_SHORT).show();
                System.out.println("chay bundleGardenAdapter ***************************************");
            }
        }
    }

    public void updateUI() {
        if (chatAdapter == null) {
            chatAdapter = new ChatAdapter(chats, getApplicationContext(), this);
            chatAdapter.setData(chats);
            mBinding.recyclerChat.setAdapter(chatAdapter);
        } else {
            chatAdapter.setData(chats);
            chatAdapter.notifyDataSetChanged();
        }
    }

    private void sendDataMessage() {
        String strMessage = mBinding.edtEnterMessage.getText().toString().trim();
        if (TextUtils.isEmpty(strMessage)) {
            return;
        }
        chats.add(new Chat(strMessage));
//        chatAdapter.notifyDataSetChanged();
        updateUI();
        mBinding.recyclerChat.scrollToPosition(chats.size() - 1);
        mBinding.edtEnterMessage.setText("");
    }

    private void checkKeyboard() {
//        final View rootView = findViewById(R.id.activity_chat_root_view);
        final View rootViewChat = mBinding.activityChatRootView;
        rootViewChat.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                rootViewChat.getWindowVisibleDisplayFrame(rect);
                int heightDiff =rootViewChat.getRootView().getHeight() - rect.height();
                if (heightDiff > 0.25 * rootViewChat.getRootView().getHeight()) {
                    if (chats.size() >0) {
                        mBinding.recyclerChat.scrollToPosition(chats.size() - 1);
                        rootViewChat.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            }
        });
    }

    @Override
    public void clickChat(Chat chat) {
        Toast.makeText(this, "show text: " + chat.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
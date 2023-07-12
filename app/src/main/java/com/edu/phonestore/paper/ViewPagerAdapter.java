package com.edu.phonestore.paper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.edu.phonestore.fragment.CartFragment;
import com.edu.phonestore.fragment.MainFragment;
import com.edu.phonestore.fragment.MessageFragment;
import com.edu.phonestore.fragment.NotificationFragment;
import com.edu.phonestore.fragment.PersonFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new MainFragment();
            case 1:
                return new MessageFragment();
            case 2:
                return new NotificationFragment();
            case 3:
                return new CartFragment();
            case 4:
                return new PersonFragment();
            default:
                return new CartFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }


}

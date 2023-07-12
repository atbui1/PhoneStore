package com.edu.phonestore.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;

import com.edu.phonestore.R;
import com.edu.phonestore.databinding.DialogOneOptionBinding;

public class AppDialog {

    private DialogOneOptionBinding mBinding;
    private View mView;

    private Context context;

    public AppDialog(Context context) {
        this.context = context;
    }

    public void showDialogOneOption(String txtErr, String txtOption, String bgColor, String txtColor) {
        Dialog dialog = new Dialog(context);
        mBinding = DialogOneOptionBinding.inflate(dialog.getLayoutInflater());
        mView = mBinding.getRoot();
        dialog.setContentView(mView);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);

        mBinding.txtDetailErr.setText(txtErr);
        mBinding.btnClose.setText(txtOption);
//        mBinding.btnClose.setBackgroundResource(R.drawable.bg_edt);
        mBinding.btnClose.setBackgroundColor(Color.parseColor(bgColor));
        mBinding.btnClose.setTextColor(Color.parseColor(txtColor));
        mBinding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}

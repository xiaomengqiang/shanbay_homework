package com.wangqiang.shanbayhomework.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.*;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.triggertrap.seekarc.SeekArc;
import com.wangqiang.shanbayhomework.R;

import org.json.JSONObject;

/**
 * 提示对话框工具类
 */
public class PromptDialogUtil {
    private Dialog dialog;
    private Context context;
    private int level;
    private OnClickListener onClickListener;
    /**
     * 提示对话框构造函数
     * @param context         当前上下文
     * @param onClickListener 提示框按钮点击事件回调监听器
     */
    public PromptDialogUtil(Context context, OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
        level = 6;
    }

    public PromptDialogUtil(Context context) {
        this.context = context;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    /**
     * 设置提示对话框，这种对话框样式有提示标题，有内容，确定和取消按钮
     */
    public void showDialog() {
        if (dialog != null){
            dialog.show();
            return;
        }
        dialog = new Dialog(context, R.style.dialog);
        dialog.getCurrentFocus();
        dialog.setCanceledOnTouchOutside(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout);
        SeekArc seekArc = (SeekArc) dialog.findViewById(R.id.seekArc);
        final TextView tvLevel = (TextView) dialog.findViewById(R.id.tvLevel);
        seekArc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser) {
                if (progress <= 8) {
                    tvLevel.setText("0级");
                } else if (progress <= 25) {
                    tvLevel.setText("1级");
                } else if (progress <= 41) {
                    tvLevel.setText("2级");
                } else if (progress <= 58) {
                    tvLevel.setText("3级");
                } else if (progress <= 75) {
                    tvLevel.setText("4级");
                } else if (progress <= 91) {
                    tvLevel.setText("5级");
                } else if (progress <= 100) {
                    tvLevel.setText("6级");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {
            }

            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {
                if (seekArc.getProgress() <= 8) {
                    seekArc.setProgress(0);
                    level = 0;
                } else if (seekArc.getProgress() <= 25) {
                    seekArc.setProgress(17);
                    level = 1;
                } else if (seekArc.getProgress() <= 41) {
                    seekArc.setProgress(34);
                    level = 2;
                } else if (seekArc.getProgress() <= 58) {
                    seekArc.setProgress(51);
                    level = 3;
                } else if (seekArc.getProgress() <= 75) {
                    seekArc.setProgress(68);
                    level = 4;
                } else if (seekArc.getProgress() <= 91) {
                    seekArc.setProgress(85);
                    level = 5;
                } else if (seekArc.getProgress() <= 100) {
                    seekArc.setProgress(100);
                    level = 6;
                }
            }
        });
        Button btnClose = (Button) dialog.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.show();
    }

    public Dialog getDialog() {
        return dialog;
    }

    public int getLevel(){
        return level;
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public boolean isShowing() {
        if (dialog == null)
            return false;
        else
            return dialog.isShowing();
    }

}

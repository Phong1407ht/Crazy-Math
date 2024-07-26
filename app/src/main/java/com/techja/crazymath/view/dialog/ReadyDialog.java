package com.techja.crazymath.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.techja.crazymath.R;

public class ReadyDialog extends Dialog {
    private final View.OnClickListener callBack;
    private TextView tvText, tvOk;

    public ReadyDialog(@NonNull Context context, View.OnClickListener event) {
        super(context, R.style.Dialog_FullScreen);
        setContentView(R.layout.view_ready);
        this.callBack = event;
        initViews();
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    private void initViews() {
        tvText = findViewById(R.id.tv_text);
        tvOk = findViewById(R.id.tv_ok);
        tvOk.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(getContext(), androidx.appcompat.R.anim.abc_fade_in));
            callBack.onClick(v);
            dismiss();
        });
    }

    public void setLoseInfo() {
        tvText.setText(R.string.txt_lose);
        tvOk.setText(R.string.txt_play_again);
    }
}

package com.techja.crazymath.view.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class CrazyTextView extends AppCompatTextView {
    public CrazyTextView(@NonNull Context context) {
        super(context);
        initViews(context);
    }

    public CrazyTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public CrazyTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "font/Rowdies-Bold.ttf"));
    }
}

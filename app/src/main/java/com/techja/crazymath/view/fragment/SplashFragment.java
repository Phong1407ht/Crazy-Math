package com.techja.crazymath.view.fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.techja.crazymath.databinding.FragmentSplashBinding;
import com.techja.crazymath.viewmodel.CommonVM;

public class SplashFragment extends BaseFragment<FragmentSplashBinding, CommonVM> {
    public static final String TAG = SplashFragment.class.getName();

    @Override
    protected void initViews() {
        Log.i(TAG, "initViews...");
        new Handler().postDelayed(this::gotoMainScreen, 2000);
    }

    private void gotoMainScreen() {
        callBack.showFragment(MainFragment.TAG, null, false);
    }

    @Override
    protected Class<CommonVM> getClassViewModel() {
        return CommonVM.class;
    }

    @Override
    protected FragmentSplashBinding initViewBinding(@NonNull LayoutInflater inflater,
                                                    @Nullable ViewGroup container) {
        return FragmentSplashBinding.inflate(inflater, container, false);
    }
}

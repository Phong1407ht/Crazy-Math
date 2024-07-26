package com.techja.crazymath.view.activity;

import com.techja.crazymath.databinding.ActivityHomeBinding;
import com.techja.crazymath.view.fragment.SplashFragment;
import com.techja.crazymath.viewmodel.CommonVM;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, CommonVM> {

    @Override
    public void backToPrevious() {
        onBackPressed();
    }

    @Override
    protected void initViews() {
        showFragment(SplashFragment.TAG, null, false);
    }

    @Override
    protected ActivityHomeBinding initViewBinding() {
        return ActivityHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    protected Class<CommonVM> initViewModel() {
        return CommonVM.class;
    }
}
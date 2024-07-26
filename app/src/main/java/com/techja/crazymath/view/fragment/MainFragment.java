package com.techja.crazymath.view.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.techja.crazymath.CommonUtils;
import com.techja.crazymath.R;
import com.techja.crazymath.databinding.FragmentMainBinding;
import com.techja.crazymath.view.dialog.ReadyDialog;
import com.techja.crazymath.viewmodel.MainViewModel;

public class MainFragment extends BaseFragment<FragmentMainBinding, MainViewModel> {
    public static final String TAG = MainFragment.class.getName();
    private static final String KEY_BEST = "KEY_BEST";

    @Override
    protected void initViews() {
        viewModel.initExpression();
        viewModel.getExpression().observe(this, s -> {
            if (s == null || s.isEmpty()) return;
            binding.tvExpression.setText(s);
        });

        viewModel.getAnsA().observe(this, s -> binding.tvAnswerA.setText(String.format("%s", s)));
        viewModel.getAnsB().observe(this, s -> binding.tvAnswerB.setText(String.format("%s", s)));
        viewModel.getAnsC().observe(this, s -> binding.tvAnswerC.setText(String.format("%s", s)));
        viewModel.getBest().observe(this, s -> binding.tvBest.setText(String.format("Best: %s", s)));
        viewModel.getScore().observe(this, s -> binding.tvScore.setText(String.format("Score: %s", s)));
        viewModel.getTime().observe(this, s -> {
            if (s < 0) return;
            binding.tvTime.setText(String.format("%s", s));
            if (s == 0) {
                gameOverUI();
            }
        });
        String bestScore = CommonUtils.getInstance().getPref(KEY_BEST);
        if (bestScore != null) {
            viewModel.getBest().postValue(Integer.parseInt(bestScore));
        }
        binding.tvTime.setOnClickListener(this);
        binding.tvAnswerA.setOnClickListener(this);
        binding.tvAnswerB.setOnClickListener(this);
        binding.tvAnswerC.setOnClickListener(this);

        startGame();
    }

    private void startGame() {
        final ReadyDialog dialog = new ReadyDialog(context, v -> viewModel.startCountdown());
        dialog.show();
    }

    @Override
    protected void clickView(View view) {
        if (view.getId() == R.id.tv_answer_a ||
                view.getId() == R.id.tv_answer_b ||
                view.getId() == R.id.tv_answer_c) {
            boolean rs = viewModel.checkAnswer(((TextView) view).getText().toString());
            if (!rs) {
                viewModel.gameOver();
            }
        }
    }

    private void gameOverUI() {
        Log.i(TAG, "gameOver...");
        int score = 0;
        int best = 0;
        if (viewModel.getScore().getValue() != null) {
            score = viewModel.getScore().getValue();
        }
        if (viewModel.getBest().getValue() != null) {
            best = viewModel.getBest().getValue();
        }
        if (score > best) {
            viewModel.getBest().postValue(score);
            CommonUtils.getInstance().savePref(KEY_BEST, score + "");
        }

        final ReadyDialog dialog = new ReadyDialog(context, v -> viewModel.playAgain());
        dialog.setLoseInfo();
        dialog.show();
    }

    @Override
    protected Class<MainViewModel> getClassViewModel() {
        return MainViewModel.class;
    }

    @Override
    protected FragmentMainBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentMainBinding.inflate(inflater, container, false);
    }

    @Override
    public void onDestroy() {
        viewModel.getThread().interrupt();
        super.onDestroy();
    }
}
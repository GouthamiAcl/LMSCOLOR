package com.sky.workbook.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.sky.workbook.IntroActivity;
import com.sky.workbook.R;


public class SplaceActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splace);

        countDownTimer = new CountDownTimer(1000, 100) {
            public void onTick(long millisUntilFinished) {
                // Use for show time progress
            }
            public void onFinish() {
                    Intent intent = new Intent(SplaceActivity.this, IntroActivity.class);
                    startActivity(intent);
            }
        }.start();

    }


}

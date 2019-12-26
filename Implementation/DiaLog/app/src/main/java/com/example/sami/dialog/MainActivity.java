package com.example.sami.dialog;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    Runnable runnable;
    private ImageView welcomeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcomeImg = findViewById(R.id.welcome_img);

        welcomeImg.animate().alpha(2000).setDuration(0);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent dsp = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(dsp);
                finish();
            }
        }, 2000);
    }
}

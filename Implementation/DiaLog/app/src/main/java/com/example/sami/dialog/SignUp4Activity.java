package com.example.sami.dialog;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp4Activity extends AppCompatActivity {
    private User user;
    private TextView textLowLimit;
    private SeekBar seekbaarLowLimit;
    private int startLowLimit = 8;
    private int maxLowLimit = 10;
    float seekBarValueLowLimit;

    private TextView textUpperLimit;
    private SeekBar seekbaarUpperLimit;
    private int startUpperLimit = 4;
    private int maxUpperLimit = 24;
    float seekBarValueUpperLimit;

    private TextView textUnterzuckerung;
    private SeekBar seekbaarUnterzuckerung;
    private int startUnterzuckerung = 6;
    private int maxUnterzuckerung = 9;
    float seekBarValueUnterzuckerung;

    private TextView textÜberzuckerung;
    private SeekBar seekbaarÜberzuckerung;
    private int startÜberzuckerung = 7;
    private int maxÜberzuckerung = 18;
    float seekBarValueÜberzuckerung;

    private EditText editCorrection;
    private EditText editBeFactor;

    private Button signUp;
    private TextView quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up4);
        Intent intent = getIntent();
        user = intent.getParcelableExtra("User");

        textLowLimit = findViewById(R.id.text_low_limit);
        seekbaarLowLimit = findViewById(R.id.seekbar_low_limit);
        seekbaarLowLimit.setMax(maxLowLimit);
        seekbaarLowLimit.setProgress(startLowLimit);
        seekBarValueLowLimit = (float) startLowLimit / 2 + 1.0f;
        textLowLimit.setText("unter " + Float.valueOf(seekBarValueLowLimit) + " mmol/L");

        seekbaarLowLimit.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValueLowLimit = (float) progress / 2 + 1.0f;
                textLowLimit.setText("unter " + String.valueOf(seekBarValueLowLimit) + " mmol/L");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        textUpperLimit = findViewById(R.id.text_upper_limit);
        seekbaarUpperLimit = findViewById(R.id.seekbar_upper_limit);
        seekbaarUpperLimit.setMax(maxUpperLimit);
        seekbaarUpperLimit.setProgress(startUpperLimit);
        seekBarValueUpperLimit = (float) startUpperLimit / 2 + 6.0f;
        textUpperLimit.setText("ab " + Float.valueOf(seekBarValueUpperLimit) + " mmol/L");

        seekbaarUpperLimit.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValueUpperLimit = (float) progress / 2 + 6.0f;
                textUpperLimit.setText("ab " + String.valueOf(seekBarValueUpperLimit) + " mmol/L");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        textUnterzuckerung = findViewById(R.id.text_unterzuckerung);
        seekbaarUnterzuckerung = findViewById(R.id.seekbar_unterzuckerung);
        seekbaarUnterzuckerung.setMax(maxUnterzuckerung);
        seekbaarUnterzuckerung.setProgress(startUnterzuckerung);
        seekBarValueUnterzuckerung = (float) startUnterzuckerung / 2 + 0.5f;
        textUnterzuckerung.setText("unter " + Float.valueOf(seekBarValueUnterzuckerung) + " mmol/L");
        //seekbaarUnterzuckerung.incrementProgressBy(10);

        seekbaarUnterzuckerung.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValueUnterzuckerung = (float) progress / 2 + 0.5f;
                textUnterzuckerung.setText("unter " + seekBarValueUnterzuckerung + " mmol/L");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        textÜberzuckerung = findViewById(R.id.text_überzuckerung);
        seekbaarÜberzuckerung = findViewById(R.id.seekbar_überzuckerung);
        seekbaarÜberzuckerung.setMax(maxÜberzuckerung);
        seekbaarÜberzuckerung.setProgress(startÜberzuckerung);
        seekBarValueÜberzuckerung = (float) startÜberzuckerung / 2 + 9.0f;
        textÜberzuckerung.setText("ab " + Float.valueOf(seekBarValueÜberzuckerung) + " mmol/L");
        //seekbaarUnterzuckerung.incrementProgressBy(10);

        seekbaarÜberzuckerung.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValueÜberzuckerung = (float) progress / 2 + 9.0f;
                textÜberzuckerung.setText("ab " + String.valueOf(seekBarValueÜberzuckerung) + " mmol/L");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        editCorrection = findViewById(R.id.input_correction);
        editBeFactor = findViewById(R.id.input_be_factor);


        signUp = findViewById(R.id.sign_up_button);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (seekBarValueUnterzuckerung > seekBarValueLowLimit || seekBarValueÜberzuckerung < seekBarValueUpperLimit) {
                    Toast toast = Toast.makeText(getBaseContext(), " Dein Zielbereich muss sich im Bereich der Unter- und \n Überzuckerungen befinden. ", Toast.LENGTH_LONG);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.show();
                    return;
                } else if (editCorrection.getText().toString().equals("") || editBeFactor.getText().toString().equals("")) {
                    if (editCorrection.getText().toString().equals(""))
                        editCorrection.setHintTextColor(Color.RED);
                    if (editBeFactor.getText().toString().equals(""))
                        editBeFactor.setHintTextColor(Color.RED);
                    Toast toast = Toast.makeText(getBaseContext(), " Bitte vervollständige deine Angaben ", Toast.LENGTH_LONG);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.show();
                    return;
                }
                if (Float.valueOf(editCorrection.getText().toString()) > 18.0) {
                    Toast toast = Toast.makeText(getBaseContext(), " Eine Einheit Insulin kann deinen Blutzucker \n  nicht mehr als 18.0 mmol/L senken. ", Toast.LENGTH_LONG);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.show();
                    return;
                }

                if (Float.valueOf(editBeFactor.getText().toString()) > 12.0) {
                    Toast toast = Toast.makeText(getBaseContext(), " Du kannst maximal 12.0 IE für eine BE/KE angeben. ", Toast.LENGTH_LONG);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.show();
                    return;
                } else {
                    user.setLowlimit(seekBarValueLowLimit);
                    user.setUpperLimit(seekBarValueUpperLimit);
                    user.setLowSugar(seekBarValueUnterzuckerung);
                    user.setUpperSugar(seekBarValueÜberzuckerung);
                    user.setCorrectionFactor(Float.valueOf(editCorrection.getText().toString()));
                    user.setBeFactor(Float.valueOf(editBeFactor.getText().toString()));

                    changeUser(user);
                    Intent intent = new Intent(SignUp4Activity.this, WelcomeActivity.class);
                    intent.putExtra("User", user);
                    startActivity(intent);
                }


            }
        });
        quit = findViewById(R.id.text_back);

        //bei Click auf "Abbrechen", LoginActivity öffnen
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteUser(user);
                Intent intent = new Intent(SignUp4Activity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void changeUser(User user){
        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<User> call = jsonPlaceHolderApi.putUser(user.getId(), user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                return;

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                return;
            }
        });
    }

    public void deleteUser(User user){
        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<Void> call = jsonPlaceHolderApi.deleteUser(user.getId());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


}


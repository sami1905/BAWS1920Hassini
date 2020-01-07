package com.example.sami.dialog;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AktivitaetActivity extends AppCompatActivity {

    private User user;
    private int whichFragment;


    private TextView textSchlafen;
    private SeekBar seekbaarSchlafen;
    private int startSchlafen = 0;
    private int maxSchlafen = 48;
    float seekBarValueSchlafen;

    private TextView textSitzen;
    private SeekBar seekbaarSitzen;
    private int startSitzen = 0;
    private int maxSitzen = 48;
    float seekBarValueSitzen;

    private TextView textKaum;
    private SeekBar seekbaarKaum;
    private int startKaum = 0;
    private int maxKaum = 48;
    float seekBarValueKaum;

    private TextView textWenig;
    private SeekBar seekbaarWenig;
    private int startWenig = 0;
    private int maxWenig = 48;
    float seekBarValueWenig;

    private TextView textStehen;
    private SeekBar seekbaarStehen;
    private int startStehen = 0;
    private int maxStehen = 48;
    float seekBarValueStehen;

    private TextView textAnstrengend;
    private SeekBar seekbaarAnstrengend;
    private int startAnstrengend = 0;
    private int maxAnstrengend = 48;
    float seekBarValueAnstrengend;

    private Button mSave;
    private TextView mBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktivitaet);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        user = intent.getParcelableExtra("User");
        whichFragment = intent.getIntExtra("Fragment", 0);


        startSchlafen = (int) (user.getCalorieDegreeOne()*2);
        startSitzen = (int) (user.getCalorieDegreeTwo()*2);
        startKaum = (int) (user.getCalorieDegreeThree()*2);
        startWenig = (int) (user.getCalorieDegreeFour()*2);
        startStehen = (int) (user.getCalorieDegreeFive()*2);
        startAnstrengend = (int) (user.getCalorieDegreeSix()*2);


        textSchlafen = findViewById(R.id.text_schlafen);
        seekbaarSchlafen = findViewById(R.id.seekbar_schlafen);
        seekbaarSchlafen.setMax(maxSchlafen);
        seekbaarSchlafen.setProgress(startSchlafen);
        seekBarValueSchlafen = (float) startSchlafen;
        textSchlafen.setText( " " + Float.valueOf(seekBarValueSchlafen/2)  + " h");

        seekbaarSchlafen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValueSchlafen = (float) progress;
                textSchlafen.setText(" " + String.valueOf(seekBarValueSchlafen/2) + " h");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        textSitzen = findViewById(R.id.text_sitzen);
        seekbaarSitzen = findViewById(R.id.seekbar_sitzen);
        seekbaarSitzen.setMax(maxSitzen);
        seekbaarSitzen.setProgress(startSitzen);
        seekBarValueSitzen = (float) startSitzen;
        textSitzen.setText( " " + Float.valueOf(seekBarValueSitzen/2)  + " h");

        seekbaarSitzen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValueSitzen = (float) progress;
                textSitzen.setText(" " + String.valueOf(seekBarValueSitzen/2) + " h");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        textKaum = findViewById(R.id.text_kaum);
        seekbaarKaum = findViewById(R.id.seekbar_kaum);
        seekbaarKaum.setMax(maxKaum);
        seekbaarKaum.setProgress(startKaum);
        seekBarValueKaum = (float) startKaum;
        textKaum.setText( " " + Float.valueOf(seekBarValueKaum/2)  + " h");

        seekbaarKaum.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValueKaum = (float) progress;
                textKaum.setText(" " + String.valueOf(seekBarValueKaum/2) + " h");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        textWenig = findViewById(R.id.text_wenig);
        seekbaarWenig = findViewById(R.id.seekbar_wenig);
        seekbaarWenig.setMax(maxWenig);
        seekbaarWenig.setProgress(startWenig);
        seekBarValueWenig = (float) startWenig;
        textWenig.setText( " " + Float.valueOf(seekBarValueWenig/2)  + " h");

        seekbaarWenig.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValueWenig = (float) progress;
                textWenig.setText(" " + String.valueOf(seekBarValueWenig/2) + " h");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        textStehen = findViewById(R.id.text_stehen);
        seekbaarStehen = findViewById(R.id.seekbar_stehen);
        seekbaarStehen.setMax(maxStehen);
        seekbaarStehen.setProgress(startStehen);
        seekBarValueStehen = (float) startStehen;
        textStehen.setText( " " + Float.valueOf(seekBarValueStehen/2)  + " h");

        seekbaarStehen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValueStehen = (float) progress;
                textStehen.setText(" " + String.valueOf(seekBarValueStehen/2) + " h");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        textAnstrengend = findViewById(R.id.text_anstrengende);
        seekbaarAnstrengend = findViewById(R.id.seekbar_anstrengende);
        seekbaarAnstrengend.setMax(maxAnstrengend);
        seekbaarAnstrengend.setProgress(startAnstrengend);
        seekBarValueAnstrengend = (float) startAnstrengend;
        textAnstrengend.setText( " " + Float.valueOf(seekBarValueAnstrengend/2)  + " h");

        seekbaarAnstrengend.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValueAnstrengend = (float) progress;
                textAnstrengend.setText(" " + String.valueOf(seekBarValueAnstrengend/2) + " h");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        mSave = findViewById(R.id.save_button);

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float checkF = seekBarValueSchlafen/2 + seekBarValueSitzen/2 + seekBarValueKaum/2 + seekBarValueWenig/2 + seekBarValueStehen/2 + seekBarValueAnstrengend/2;
                if(checkF > 24.0f){
                    Toast toast = Toast.makeText(getBaseContext(), " Du hast insgesamt nur 24 Stunden auf die jeweiligen Aktivitäten zu verteilen. ", Toast.LENGTH_LONG);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.show();
                }

                else{
                    user.setCalorieDegreeOne(seekBarValueSchlafen/2);
                    user.setCalorieDegreeTwo(seekBarValueSitzen/2);
                    user.setCalorieDegreeThree(seekBarValueKaum/2);
                    user.setCalorieDegreeFour(seekBarValueWenig/2);
                    user.setCalorieDegreeFive(seekBarValueStehen/2);
                    user.setCalorieDegreeSix(seekBarValueAnstrengend/2);

                    changeUser(user);


                    Intent intent = new Intent(AktivitaetActivity.this, HomeActivity.class);
                    Bundle extra = new Bundle();
                    extra.putParcelable("User", user);
                    extra.putInt("Fragment", whichFragment);
                    intent.putExtras(extra);
                    startActivity(intent);
                }

            }
        });






        mBack = findViewById(R.id.text_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AktivitaetActivity.this, HomeActivity.class);
                Bundle extra = new Bundle();
                extra.putParcelable("User", user);
                extra.putInt("Fragment", whichFragment);
                intent.putExtras(extra);
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
}

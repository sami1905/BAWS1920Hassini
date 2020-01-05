package com.example.sami.dialog;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Welcome3Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private User user;
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

    private Spinner spinnerWeightGoal;

    private Button buttonBestätigen;
    private TextView textÜberspringen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome3);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("User");

        textSchlafen = findViewById(R.id.text_schlafen);
        seekbaarSchlafen = findViewById(R.id.seekbar_schlafen);
        seekbaarSchlafen.setMax(maxSchlafen);
        seekbaarSchlafen.setProgress(startSchlafen);
        seekBarValueSchlafen = (float) startSchlafen;
        textSchlafen.setText( " " + Float.valueOf(seekBarValueSchlafen)  + " h");

        seekbaarSchlafen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValueSchlafen = (float) progress/2;
                textSchlafen.setText(" " + String.valueOf(seekBarValueSchlafen) + " h");

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
        textSitzen.setText( " " + Float.valueOf(seekBarValueSitzen)  + " h");

        seekbaarSitzen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValueSitzen = (float) progress/2;
                textSitzen.setText(" " + String.valueOf(seekBarValueSitzen) + " h");

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
        textKaum.setText( " " + Float.valueOf(seekBarValueKaum)  + " h");

        seekbaarKaum.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValueKaum = (float) progress/2;
                textKaum.setText(" " + String.valueOf(seekBarValueKaum) + " h");

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
        textWenig.setText( " " + Float.valueOf(seekBarValueWenig)  + " h");

        seekbaarWenig.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValueWenig = (float) progress/2;
                textWenig.setText(" " + String.valueOf(seekBarValueWenig) + " h");

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
        textStehen.setText( " " + Float.valueOf(seekBarValueStehen)  + " h");

        seekbaarStehen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValueStehen = (float) progress/2;
                textStehen.setText(" " + String.valueOf(seekBarValueStehen) + " h");

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
        textAnstrengend.setText( " " + Float.valueOf(seekBarValueAnstrengend)  + " h");

        seekbaarAnstrengend.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValueAnstrengend = (float) progress/2;
                textAnstrengend.setText(" " + String.valueOf(seekBarValueAnstrengend) + " h");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        spinnerWeightGoal = findViewById(R.id.spinner_weight_goal);
        ArrayAdapter<CharSequence> adapterWeightGoal = ArrayAdapter.createFromResource(this, R.array.weight_goal, R.layout.layout_spinner);
        adapterWeightGoal.setDropDownViewResource(R.layout.layout_spinner_drop_down);
        spinnerWeightGoal.setAdapter(adapterWeightGoal);
        spinnerWeightGoal.setOnItemSelectedListener(this);
        spinnerWeightGoal.setSelection(1);

        buttonBestätigen = findViewById(R.id.bestätigen_button);
        buttonBestätigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float checkF = seekBarValueSchlafen + seekBarValueSitzen + seekBarValueKaum + seekBarValueWenig + seekBarValueStehen + seekBarValueAnstrengend;
                if(checkF > 24.0f){
                    Toast toast = Toast.makeText(getBaseContext(), " Du hast insgesamt nur 24 Stunden auf die jeweiligen Aktivitäten zu verteilen. ", Toast.LENGTH_LONG);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.show();
                }

                else{
                    user.setCalorieGegreeOne(seekBarValueSchlafen);
                    user.setCalorieGegreeTwo(seekBarValueSitzen);
                    user.setCalorieGegreeThree(seekBarValueKaum);
                    user.setCalorieGegreeFour(seekBarValueWenig);
                    user.setCalorieGegreeFive(seekBarValueStehen);
                    user.setCalorieGegreeSix(seekBarValueAnstrengend);
                    user.setWeightGoal(spinnerWeightGoal.getSelectedItem().toString());

                    changeUser(user);

                    Intent intent = new Intent(Welcome3Activity.this, HomeActivity.class);
                    intent.putExtra("User", user);
                    startActivity(intent);
                }
            }
        });

        textÜberspringen = findViewById(R.id.text_überspringen);
        textÜberspringen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome3Activity.this, HomeActivity.class);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.setBackgroundColor(Color.WHITE);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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

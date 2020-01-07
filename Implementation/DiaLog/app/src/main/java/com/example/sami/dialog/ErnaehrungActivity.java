package com.example.sami.dialog;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ErnaehrungActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private User user;
    private int whichFragment;
    private Fragment selectedFragment = null;
    private Bundle userBdl = new Bundle();

    private EditText mWeight;
    private EditText mHeight;
    private Spinner spinnerWeightGoal;
    private Spinner mUnitKH;
    private Button mSave;
    private TextView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ernaehrung);
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        user = intent.getParcelableExtra("User");
        whichFragment = intent.getIntExtra("Fragment", 0);

        mWeight = findViewById(R.id.edit_mein_gewicht);
        mWeight.setText("" + user.getWeight());

        mHeight = findViewById(R.id.edit_meine_größe);
        mHeight.setText("" + user.getHeight());

        spinnerWeightGoal = findViewById(R.id.spinner_weight_goal);
        ArrayAdapter<CharSequence> adapterWeightGoal = ArrayAdapter.createFromResource(this, R.array.weight_goal, R.layout.layout_spinner);
        adapterWeightGoal.setDropDownViewResource(R.layout.layout_spinner_drop_down);
        spinnerWeightGoal.setAdapter(adapterWeightGoal);
        spinnerWeightGoal.setOnItemSelectedListener(this);

        if(user.getWeightGoal().equals("abnehmen")) spinnerWeightGoal.setSelection(0);
        if(user.getWeightGoal().equals("Gewicht halten")) spinnerWeightGoal.setSelection(1);
        if(user.getWeightGoal().equals("zunehmen")) spinnerWeightGoal.setSelection(2);

        mUnitKH = findViewById(R.id.spinner_unit_kh);
        ArrayAdapter<CharSequence> adapterUnitKH = ArrayAdapter.createFromResource(this, R.array.unit_kh, R.layout.layout_spinner);
        adapterUnitKH.setDropDownViewResource(R.layout.layout_spinner_drop_down);
        mUnitKH.setAdapter(adapterUnitKH);
        mUnitKH.setOnItemSelectedListener(this);

        if(user.getUnitKH().equals("BE")) mUnitKH.setSelection(1);
        if(user.getUnitKH().equals("KE")) mUnitKH.setSelection(2);


        mSave = findViewById(R.id.save_button);

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mHeight.getText().toString().equals("")|| mWeight.getText().toString().equals("") ||
                        mUnitKH.getSelectedItem().toString().equals("")) {
                    if(mWeight.getText().toString().equals("")) mWeight.setHintTextColor(Color.RED);
                    if(mHeight.getText().toString().equals("")) mHeight.setHintTextColor(Color.RED);
                    if (mUnitKH.getSelectedItem().toString().equals("")) mUnitKH.setBackgroundColor(Color.RED);
                    Toast toast = Toast.makeText(getBaseContext(), " Bitte vervollständige deine Angaben. ", Toast.LENGTH_LONG);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.show();
                    return;


                }
                else if(Float.valueOf(mWeight.getText().toString()) > 400.0){
                    Toast toast = Toast.makeText(getBaseContext(), " Dein Körpergewicht muss unter 400 kg liegen. ", Toast.LENGTH_LONG);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.show();
                    return;

                }

                else if(Float.valueOf(mHeight.getText().toString()) > 250 || Float.valueOf(mHeight.getText().toString()) < 80){
                    Toast toast = Toast.makeText(getBaseContext(), " Deine Körpergöße muss zwischen 80 und 250 cm liegen. ", Toast.LENGTH_LONG);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.show();
                    return;
                }
                else{
                    user.setHeight(Integer.parseInt(mHeight.getText().toString()));
                    user.setWeight(Float.valueOf(mWeight.getText().toString()));
                    user.setWeightGoal(spinnerWeightGoal.getSelectedItem().toString());
                    user.setUnitKH(mUnitKH.getSelectedItem().toString());

                    changeUser(user);

                    Intent intent = new Intent(ErnaehrungActivity.this, HomeActivity.class);
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
                Intent intent = new Intent(ErnaehrungActivity.this, HomeActivity.class);
                Bundle extra = new Bundle();
                extra.putParcelable("User", user);
                extra.putInt("Fragment", whichFragment);
                intent.putExtras(extra);
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

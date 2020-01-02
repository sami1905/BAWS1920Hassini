package com.example.sami.dialog;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner dmType;
    private Spinner insulin;
    private Spinner tablets;
    private Spinner unitBZ;
    private Spinner unitKH;
    private TextView quit;
    private Button signUp;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("User");


        dmType = findViewById(R.id.spinner_dm_type);
        ArrayAdapter<CharSequence> adapterDmType = ArrayAdapter.createFromResource(this, R.array.dm_type, android.R.layout.simple_spinner_item);
        adapterDmType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dmType.setAdapter(adapterDmType);
        dmType.setOnItemSelectedListener(this);

        insulin = findViewById(R.id.spinner_insulin);
        ArrayAdapter<CharSequence> adapterInsulin = ArrayAdapter.createFromResource(this, R.array.insulin, android.R.layout.simple_spinner_item);
        adapterInsulin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        insulin.setAdapter(adapterInsulin);
        insulin.setOnItemSelectedListener(this);

        tablets = findViewById(R.id.spinner_tablets);
        ArrayAdapter<CharSequence> adapterTablets = ArrayAdapter.createFromResource(this, R.array.janein, android.R.layout.simple_spinner_item);
        adapterTablets.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tablets.setAdapter(adapterTablets);
        tablets.setOnItemSelectedListener(this);

        unitBZ = findViewById(R.id.spinner_unit_bz);
        ArrayAdapter<CharSequence> adapterUnitBz = ArrayAdapter.createFromResource(this, R.array.unit_bz, android.R.layout.simple_spinner_item);
        adapterUnitBz.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitBZ.setAdapter(adapterUnitBz);
        unitBZ.setOnItemSelectedListener(this);

        unitKH = findViewById(R.id.spinner_unit_kh);
        ArrayAdapter<CharSequence> adapterUnitKh = ArrayAdapter.createFromResource(this, R.array.unit_kh, android.R.layout.simple_spinner_item);
        adapterUnitKh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitKH.setAdapter(adapterUnitKh);
        unitKH.setOnItemSelectedListener(this);

        signUp = findViewById(R.id.sign_up_button);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(dmType.getSelectedItem().toString().equals("") || insulin.getSelectedItem().toString().equals("") || tablets.getSelectedItem().toString().equals("") ||
                        unitBZ.getSelectedItem().toString().equals("")|| unitKH.getSelectedItem().toString().equals("")) {
                    if (dmType.getSelectedItem().toString().equals(""))
                        dmType.setBackgroundColor(Color.RED);
                    if (insulin.getSelectedItem().toString().equals(""))
                        insulin.setBackgroundColor(Color.RED);
                    if (tablets.getSelectedItem().toString().equals(""))
                        tablets.setBackgroundColor(Color.RED);
                    if (unitBZ.getSelectedItem().toString().equals(""))
                        unitBZ.setBackgroundColor(Color.RED);
                    if (unitKH.getSelectedItem().toString().equals(""))
                        unitKH.setBackgroundColor(Color.RED);
                    Toast toast = Toast.makeText(getBaseContext(), " Bitte vervollständige deine Angaben. ", Toast.LENGTH_LONG);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.show();
                    return;
                }
                else{
                    user.setDmType(dmType.getSelectedItem().toString());
                    user.setInsulin(insulin.getSelectedItem().toString());
                    user.setTablets(0 == tablets.getSelectedItemId()-1);
                    user.setUnitBZ(unitBZ.getSelectedItem().toString());
                    user.setUnitKH(unitKH.getSelectedItem().toString());

                    if(user.getUnitBZ().equals("mg/dL")){
                        Intent intent = new Intent(SignUp2Activity.this, SignUp3Activity.class);
                        intent.putExtra("User", user);
                        startActivity(intent);
                    }

                    else if(user.getUnitBZ().equals("mmol/L")){
                        Intent intent = new Intent(SignUp2Activity.this, SignUp4Activity.class);
                        intent.putExtra("User", user);
                        startActivity(intent);
                    }
                }



            }
        });


        quit = findViewById(R.id.text_back);

        //bei Click auf "Abbrechen", LoginActivity öffnen
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp2Activity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.setBackgroundColor(Color.WHITE);
        String text = parent.getItemAtPosition(position).toString();
        if(!text.trim().isEmpty()) {
            Toast toast = Toast.makeText(parent.getContext(), "  Auswahl: " + text + "  ", Toast.LENGTH_SHORT);
            View toastView = toast.getView();
            toastView.setBackgroundResource(R.drawable.toast_drawable);
            toast.show();

        }
        return;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

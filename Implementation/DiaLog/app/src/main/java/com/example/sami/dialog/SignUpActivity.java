package com.example.sami.dialog;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "SignUpActivity";
    private User user;
    private EditText firstname;
    private EditText name;
    private TextView birthday;
    private Spinner genger;
    private EditText nickname;
    private EditText email;
    private EditText password;
    private Button signUp;
    private TextView quit;
    int i = 0;

    private DatePickerDialog.OnDateSetListener mDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstname = findViewById(R.id.input_firstname);
        name = findViewById(R.id.input_name);

        birthday = (TextView) findViewById(R.id.input_birthday);
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(SignUpActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth, mDateSetListener, year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                birthday.setText(date);
            }
        };

        genger = findViewById(R.id.spinner_genger);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genger, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genger.setAdapter(adapter);
        genger.setOnItemSelectedListener(this);

        nickname = findViewById(R.id.input_nickame);
        email = findViewById(R.id.input_email_adresse);
        password = findViewById(R.id.input_passwort);
        signUp = findViewById(R.id.registrieren_button);
        quit = findViewById(R.id.text_abbrechen);


        //bei Click auf "Weiter" die Eingabe prüfen und SignUp2Activity öffnen
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(firstname.getText().toString().equals("")){
                        Toast toast = Toast.makeText(getBaseContext(), " Bitte gebe deinen Vornamen ein. ", Toast.LENGTH_LONG);
                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.toast_drawable);
                        toast.show();
                        return;
                    }
                    else if(name.getText().toString().equals("")){
                        Toast toast = Toast.makeText(getBaseContext(), " Bitte gebe deinen Nachnamen ein. ", Toast.LENGTH_LONG);
                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.toast_drawable);
                        toast.show();
                        return;
                    }
                    else if(birthday.getText().toString().equals("")){
                        Toast toast = Toast.makeText(getBaseContext(), " Bitte gebe dein Geburtdatum ein. ", Toast.LENGTH_LONG);
                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.toast_drawable);
                        toast.show();
                        return;
                    }
                    else if(nickname.getText().toString().equals("")){
                        Toast toast = Toast.makeText(getBaseContext(), " Bitte gebe einen Benutzernamen ein. ", Toast.LENGTH_LONG);
                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.toast_drawable);
                        toast.show();
                        return;
                    }
                    else if(email.getText().toString().equals("")){
                        Toast toast = Toast.makeText(getBaseContext(), " Bitte gebe deine E-Mail-Adresse ein. ", Toast.LENGTH_LONG);
                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.toast_drawable);
                        toast.show();
                        return;
                    }
                    else if(validMailAddress(email.getText().toString()) == false){
                        Toast toast = Toast.makeText(getBaseContext(), " Bitte gebe deine korrekte E-Mail-Adresse ein. ", Toast.LENGTH_LONG);
                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.toast_drawable);
                        toast.show();
                        return;
                    }
                    else if(password.getText().toString().equals("")){
                        Toast toast = Toast.makeText(getBaseContext(), " Bitte gebe ein Passwort ein. ", Toast.LENGTH_LONG);
                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.toast_drawable);
                        toast.show();
                        return;
                    }
                    else if(validPassword(password.getText().toString()) == false) {
                        Toast toast = Toast.makeText(getBaseContext(), " Dein Passwort muss mindestens aus 8 Zeichen bestehen. ", Toast.LENGTH_LONG);
                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.toast_drawable);
                        toast.show();
                        return;
                    }
                    else{
                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }



            }
        });


        //bei Click auf "Abbrechen", LoginActivity öffnen
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



    }
    //E-Mail-Format prüfen
    boolean validMailAddress(String mailAddress)
    {
        boolean retValue = true;

        int i = mailAddress.indexOf("@");
        int j = mailAddress.indexOf(".", i);

        if (i == 0)  // Anzahl der Zeichen vor dem @
        {
            retValue = false;
        }

        if (j == -1)  // Prüft ob kein Punkt nach dem @ Zeichen kommt
        {
            retValue = false;
        }

        if ((j - i) < 2)  // Prüft Anzahl der Zeichen zwischen dem @ und dem .
        {
            retValue = false;
        }

        if (j == (mailAddress.length()-1))  // Mail Adresse muss länger sein, als die Stelle vom Punkt
        {
            retValue = false;
        }

        return retValue;
    }

    //Passwort-Format prüfen
    boolean validPassword(String password) {
        if (password.toCharArray().length < 8) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String text = parent.getItemAtPosition(position).toString();
        if(i > 0) {
            Toast toast = Toast.makeText(parent.getContext(), "  Auswahl: " + text + "  ", Toast.LENGTH_SHORT);
            View toastView = toast.getView();
            toastView.setBackgroundResource(R.drawable.toast_drawable);
            toast.show();

        }
        i++;
        return;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

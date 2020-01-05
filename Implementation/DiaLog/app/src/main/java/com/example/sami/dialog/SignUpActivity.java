package com.example.sami.dialog;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Calendar.YEAR;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "SignUpActivity";
    private int id;
    private EditText firstname;
    private EditText name;
    private TextView birthday;
    private int age;
    private EditText height;
    private EditText weight;
    private Spinner gender;
    private EditText nickname;
    private EditText email;
    private EditText password;
    private Button next;
    private TextView quit;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private User user;


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
                int year = cal.get(YEAR);
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
                Calendar cal = Calendar.getInstance();
                int currentYear = cal.get(YEAR);
                int currentMonth = cal.get(Calendar.MONTH);
                int currentDay = cal.get(Calendar.DAY_OF_MONTH);
                age = currentYear - year;
                if (month > currentMonth || month == currentMonth && day > currentDay) age--;
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                birthday.setText(date);
            }
        };


        height = findViewById(R.id.input_height);
        weight = findViewById(R.id.input_weight);
        gender = findViewById(R.id.spinner_gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender, R.layout.layout_spinner);
        adapter.setDropDownViewResource(R.layout.layout_spinner_drop_down);
        gender.setAdapter(adapter);
        gender.setOnItemSelectedListener(this);

        nickname = findViewById(R.id.input_nickame);
        email = findViewById(R.id.input_email_adresse);
        password = findViewById(R.id.input_passwort);
        next = findViewById(R.id.next_button);
        quit = findViewById(R.id.text_abbrechen);




        //bei Click auf "Weiter" die Eingabe prüfen und SignUp2Activity öffnen
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(firstname.getText().toString().equals("") || name.getText().toString().equals("") || birthday.getText().toString().equals("") ||
                            height.getText().toString().equals("")|| weight.getText().toString().equals("") || gender.getSelectedItem().toString().equals("") ||
                            nickname.getText().toString().equals("") || email.getText().toString().equals("") || password.getText().toString().equals("")) {
                        if(firstname.getText().toString().equals("")) firstname.setHintTextColor(Color.RED);
                        if(name.getText().toString().equals("")) name.setHintTextColor(Color.RED);
                        if(birthday.getText().toString().equals("")) birthday.setHintTextColor(Color.RED);
                        if(height.getText().toString().equals("")) height.setHintTextColor(Color.RED);
                        if(weight.getText().toString().equals("")) weight.setHintTextColor(Color.RED);
                        if(gender.getSelectedItem().toString().equals("")) gender.setBackgroundColor(Color.RED);
                        if(nickname.getText().toString().equals("")) nickname.setHintTextColor(Color.RED);
                        if(email.getText().toString().equals("")) email.setHintTextColor(Color.RED);
                        if(password.getText().toString().equals("")) password.setHintTextColor(Color.RED);
                        Toast toast = Toast.makeText(getBaseContext(), " Bitte vervollständige deine Angaben. ", Toast.LENGTH_LONG);
                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.toast_drawable);
                        toast.show();
                        return;
                    }

                    else if(Float.valueOf(weight.getText().toString()) > 400.0){
                        Toast toast = Toast.makeText(getBaseContext(), " Dein Körpergewicht muss unter 400 kg liegen. ", Toast.LENGTH_LONG);
                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.toast_drawable);
                        toast.show();
                        return;

                    }

                    else if(Float.valueOf(height.getText().toString()) > 250 || Float.valueOf(height.getText().toString()) < 80){
                        Toast toast = Toast.makeText(getBaseContext(), " Deine Körpergöße muss zwischen 80 und 250 cm liegen. ", Toast.LENGTH_LONG);
                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.toast_drawable);
                        toast.show();
                        return;
                    }
                    else if(validMailAddress(email.getText().toString()) == false){
                        email.setHintTextColor(Color.RED);
                        email.setTextColor(Color.RED);
                        Toast toast = Toast.makeText(getBaseContext(), " Bitte gebe deine korrekte E-Mail-Adresse ein. ", Toast.LENGTH_LONG);
                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.toast_drawable);
                        toast.show();
                        return;
                    }

                    else if(validPassword(password.getText().toString()) == false) {
                        password.setHintTextColor(Color.RED);
                        password.setTextColor(Color.RED);
                        Toast toast = Toast.makeText(getBaseContext(), " Dein Passwort muss mindestens aus 8 Zeichen bestehen. ", Toast.LENGTH_LONG);
                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.toast_drawable);
                        toast.show();
                        return;
                    }
                    else if(age < 0){
                        birthday.setHintTextColor(Color.RED);
                        birthday.setTextColor(Color.RED);
                        Toast toast = Toast.makeText(getBaseContext(), " Bitte gib dein richtiges Geburtsdatum an. ", Toast.LENGTH_LONG);
                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.toast_drawable);
                        toast.show();
                        return;
                    }
                    else {
                        checkUsers();

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
        parent.setBackgroundColor(Color.WHITE);

        return;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void checkUsers(){

        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<List<User>> call = jsonPlaceHolderApi.getUsers();

        call.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(!response.isSuccessful()){

                }

                if(response.isSuccessful() || response.code() == 200){
                    List<User> users = response.body();
                    for(User user : users){
                        id = user.getId() + 1;
                        if(nickname.getText().toString().equals(user.getNickname())){
                            Toast toast = Toast.makeText(getBaseContext(), " Benutzername ist bereits vergeben. ", Toast.LENGTH_LONG);
                            View toastView = toast.getView();
                            toastView.setBackgroundResource(R.drawable.toast_drawable);
                            toast.show();
                            return;
                        }

                        else if( email.getText().toString().equals(user.getEmail())){
                            Toast toast = Toast.makeText(getBaseContext(), " Es existiert bereits ein Konto mit der \n angegebenen E-Mail-Adresse. ", Toast.LENGTH_LONG);
                            View toastView = toast.getView();
                            toastView.setBackgroundResource(R.drawable.toast_drawable);
                            toast.show();
                            return;
                        }
                        else{

                            User newUser = new User(id, firstname.getText().toString(), name.getText().toString(), birthday.getText().toString(),
                                    Integer.parseInt(height.getText().toString()), Float.parseFloat(weight.getText().toString()), gender.getSelectedItem().toString(),
                                    nickname.getText().toString(), email.getText().toString(), password.getText().toString(), "", "", false, "", "",
                                    0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
                                    0.0f, 0.0f,0.0f, 0.0f, 0.0f,
                                    0.0f, 0.0f, "Gewicht halten", new int[]{}, new int[]{}, 0);

                            createUser(newUser);

                            Intent intent = new Intent(SignUpActivity.this, SignUp2Activity.class);
                            intent.putExtra("User", newUser);
                            startActivity(intent);

                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast toast = Toast.makeText(getBaseContext(), " Verbindungsfehler! Überprüfen Sie Ihre Internetverbindung \n oder versuchen Sie es zu einem späteren Zeitpunkt erneut!.", Toast.LENGTH_LONG);
                View toastView = toast.getView();
                toastView.setBackgroundResource(R.drawable.toast_drawable);
                toast.show();
            }
        });

    }

    public void createUser(User user) {
        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<User> call = jsonPlaceHolderApi.createUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getBaseContext(), "FEHLER-CODE " + response.code() + ": Benutzer konnte nicht hinzugefügt werden.", Toast.LENGTH_LONG);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.show();
                    return;
                }


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                return;


            }
        });
    }

}

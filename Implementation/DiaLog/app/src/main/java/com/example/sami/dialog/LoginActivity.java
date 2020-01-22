package com.example.sami.dialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private TextView registrierenText;
    private EditText editEmail;
    private EditText editPassword;
    private Button buttonLogin;
    private User user;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getConnection();

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        user = intent.getParcelableExtra("User");

        editEmail = findViewById(R.id.input_email_adresse);
        editPassword = findViewById(R.id.input_passwort);
        buttonLogin = findViewById(R.id.login_button);

        if(user != null)editEmail.setText(user.getEmail().toString());

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryLogIn();
            }
        });

        registrierenText = findViewById(R.id.registrieren_button);
        registrierenText.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openSignUp();

            }
        });


    }

    public void getConnection(){
        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<ResponseBody> call = jsonPlaceHolderApi.getConnection();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(!response.isSuccessful()){
                    Toast toast = Toast.makeText(getBaseContext(), "FEHLER-CODE " + response.code() + ": " + "Server nicht erreichbar.", Toast.LENGTH_LONG);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.show();
                    return;

                }

                if(response.isSuccessful() || response.code() == 200){

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast toast = Toast.makeText(getBaseContext(), " Verbindungsfehler! Überprüfen Sie Ihre Internetverbindung \n oder versuchen Sie es zu einem späteren Zeitpunkt erneut!.", Toast.LENGTH_LONG);
                View toastView = toast.getView();
                toastView.setBackgroundResource(R.drawable.toast_drawable);
                toast.show();
            }
        });
    }

    public void openSignUp(){
        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<ResponseBody> call = jsonPlaceHolderApi.getConnection();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(!response.isSuccessful()){
                    Toast toast = Toast.makeText(getBaseContext(), "FEHLER-CODE " + response.code() + ": " + "Server nicht erreichbar.", Toast.LENGTH_LONG);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_drawable);
                    toast.show();
                    return;

                }

                if(response.isSuccessful() || response.code() == 200){
                    Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast toast = Toast.makeText(getBaseContext(), " Verbindungsfehler! Überprüfen Sie Ihre Internetverbindung \n oder versuchen Sie es zu einem späteren Zeitpunkt erneut!.", Toast.LENGTH_LONG);
                View toastView = toast.getView();
                toastView.setBackgroundResource(R.drawable.toast_drawable);
                toast.show();
            }
        });

    }

    public void tryLogIn(){

        JsonPlaceHolderApi jsonPlaceHolderApi = RestService.getRestService().create(JsonPlaceHolderApi.class);

        Call<List<User>> call = jsonPlaceHolderApi.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(!response.isSuccessful()){

                }

                if(response.isSuccessful() || response.code() == 200){
                    List<User> users = response.body();
                    int i = 0;

                    for(User user : users){
                        if(user.getEmail().equals(editEmail.getText().toString()) && user.getPassword().equals(editPassword.getText().toString())){
                            i++;
                            User matchUser = user;
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            Bundle extra = new Bundle();
                            extra.putParcelable("User", matchUser);
                            extra.putInt("Fragment", 0);
                            intent.putExtras(extra);
                            startActivity(intent);
                        }

                    }
                    if (i == 0){
                        Toast toast = Toast.makeText(getBaseContext(), " Benutzer nicht gefunden! ", Toast.LENGTH_LONG);
                        View toastView = toast.getView();
                        toastView.setBackgroundResource(R.drawable.toast_drawable);
                        toast.show();
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



}

package com.example.sami.dialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    private TextView textView;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Intent intent = getIntent();
        user = intent.getParcelableExtra("User");
        textView = findViewById(R.id.textView);

        String text = ("Vorname: " + user.getFirstname() + "\n" + "Nachname: " + user.getName() + "\n" + "Geburtsdatum: " + user.getBirthday() + "\n" + "Alter: " + user.getAge() + "\n" +
                "Körpergröße: " + user.getHeight() + "\n" + "Körpergewicht: " + user.getWeight() + "\n" + "Geschlecht: " + user.getGenger() + "\n" + "Benutzername: " + user.getNickname() + "\n" +
                "E-Mail: " + user.getEmail() + "\n" + "Passwort: " + user.getPassword() + "\n" + "Diabetes-Typ: " + user.getDmType() + "\n" + "Insulin-Therapie: " + user.getInsulin() +
                "\n" + "Tabletten: " + user.isTablets() + "\n" + "Blutzuckereinheit: " + user.getUnitBZ() + "\n" + "Kohlenhydrate-Einheit: " + user.getUnitKH()) + "\n" +
                "Zielbereich liegt zwischen: " + user.getLowlimit() + " - " + user.getUpperLimit() + "\n" + "Unterzuckerungen ab: " + user.getLowSugar() + "\n"
                + "Überzuckerungen ab: " + user.getUpperSugar() + "\n" + "Korrekturfaktor: " + user.getCorrectionFactor() + "\n" + "BE-Faktor: " +  user.getBeFactor();

        textView.setText(text);
    }
}

package com.example.sami.dialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    private TextView textView;
    private User user;
    private Button buttonNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Intent intent = getIntent();
        user = intent.getParcelableExtra("User");
        textView = findViewById(R.id.text_wellcome);


        textView.setText("Hallo " + user.getFirstname() + ",\n" + "herzlich Willkommen bei DiaLog! Für deine Registrierung erhälst du 150 Punkte auf deinem Punktekonto. Starte gleich mit der Dokumentation deiner Blutzuckerwerte, trage Mahlzeiten und sportliche Aktivitäten in Ereignissen ein oder tausche dich mit anderen Diabetikern im Wartezimmer aus. Verwalte deine Benutzdaten und erweitere sie, um alle Funktionen von DiaLog verwenden zu können. Beim Dokumentieren von Ereignissen und Posten von Beiträgen oder Kommentaren erhälst du für deine Aktivität bei DiaLog weitere Punkte auf deinem Punktekonto und kannst so um den Highscore der DiaLog-Community kämpfen. \n Viel Spaß mit DiaLog! ");

        buttonNext = findViewById(R.id.next_button);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, Welcome2Activity.class);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        });
    }
}

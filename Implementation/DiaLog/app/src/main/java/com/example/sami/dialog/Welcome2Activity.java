package com.example.sami.dialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Welcome2Activity extends AppCompatActivity {
    private User user;
    private Button buttonNext;
    private TextView textÜberspringen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome2);
        Intent intent = getIntent();
        user = intent.getParcelableExtra("User");
        buttonNext = findViewById(R.id.next_button);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome2Activity.this, Welcome3Activity.class);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        });

        textÜberspringen = findViewById(R.id.überspringen_button);
        textÜberspringen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome2Activity.this, HomeActivity.class);
                Bundle extra = new Bundle();
                extra.putParcelable("User", user);
                extra.putInt("Fragment", 0);
                intent.putExtras(extra);
                startActivity(intent);
            }
        });
    }
}

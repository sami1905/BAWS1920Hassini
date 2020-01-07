package com.example.sami.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IchFragment extends Fragment {

    private User user;
    private TextView mNickname;
    private TextView mWeight;
    private TextView mBMI;
    private float BMI;
    private TextView mWeightGoal;
    private TextView mWeightGoalInfo;
    private Button mChangeWeight;
    private TextView mAktivitäten;
    private Button mChangeAktivität;
    private FrameLayout mPosts;
    private FrameLayout mComments;
    private FrameLayout mReports;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ich, container, false);

        if(getArguments() != null){
            user = getArguments().getParcelable("User");
        }


        mNickname = v.findViewById(R.id.text_nickname);
        mWeight = v.findViewById(R.id.dein_gewicht);
        mBMI = v.findViewById(R.id.dein_bmi);
        mWeightGoal = v.findViewById(R.id.text_weight_goal);
        mWeightGoalInfo = v.findViewById(R.id.text_weight_info);
        mChangeWeight = v.findViewById(R.id.button_gewicht_aktualisieren);
        mAktivitäten = v.findViewById(R.id.deine_aktivitäten);
        mPosts = v.findViewById(R.id.meine_posts);
        mComments = v.findViewById(R.id.meine_comments);
        mReports = v.findViewById(R.id.meine_berichte);

        mNickname.setText(user.getNickname().toString());
        mWeight.setText("Mein Gewicht: " + user.getWeight() + " kg");
        BMI = user.getWeight() /(user.getHeight()*user.getHeight())*10000;
        BMI = (float) Math.round(BMI*10);
        BMI = BMI/10;
        mBMI.setText("Mein BMI: " + BMI);
        mWeightGoal.setText("Mein Ziel: " + user.getWeightGoal());

        if(BMI < 19.0f && !user.getWeightGoal().equals("zunehmen")){
            mWeightGoalInfo.setText("Dein BMI ist sehr niedrig. Versuche an Körpergewicht zuzunehmen, um langfristig gesund und leistungsfähig zu bleiben. Setze dein Ziel auf 'zunehmen', um den idealen Kalorienbedarf für dich von DiaLog ausrechnen zu lassen. Bei einem dauerhaften BMI unter 19.0, ist ein Besuch beim Hausarzt zu empfehlen.");
        }

        if(BMI< 19.0f && user.getWeightGoal().equals("zunehmen")){
            mWeightGoalInfo.setText("Dein BMI ist sehr niedrig. Versuche an Körpergewicht zuzunehmen, um langfristig gesund und leistungsfähig zu bleiben. Bei einem dauerhaften BMI unter 19.0, ist ein Besuch beim Hausarzt zu empfehlen.");
        }

        if(BMI >= 19.0f && BMI <= 25.0f && !user.getWeightGoal().equals("Gewicht halten")){
            mWeightGoalInfo.setText("Super! Dein Gewicht ist gesund. Mit einer ausgewogenen Ernährung und regelmäßiger Bewegung wird das auch langfristig so bleiben. Setze dein Ziel auf 'Gewicht halten', um den idealen Kalorienbedarf für dich von DiaLog ausrechnen zu lassen.");
        }

        if(BMI >= 19.0f && BMI <= 25.0f && user.getWeightGoal().equals("Gewicht halten")){
            mWeightGoalInfo.setText("Super! Dein Gewicht ist gesund. Mit einer ausgewogenen Ernährung und regelmäßiger Bewegung wird das auch langfristig so bleiben.");
        }

        if(BMI > 25.0f && BMI < 30.0f && !user.getWeightGoal().equals("abnehmen")){
            mWeightGoalInfo.setText("Dein BMI ist leicht erhöht. Achte auf deine Ernährung und bewege dich regelmäßig. Setze dein Ziel auf 'abnehmen', um den idealen Kalorienbedarf für dich von DiaLog ausrechnen zu lassen.");
        }

        if(BMI > 25.0f && BMI < 30.0f && user.getWeightGoal().equals("abnehmen")){
            mWeightGoalInfo.setText("Dein BMI ist leicht erhöht. Achte auf deine Ernährung und bewege dich regelmäßig.");
        }

        if(BMI >= 30.0f && !user.getWeightGoal().equals("abnehmen")){
            mWeightGoalInfo.setText("Dein BMI weist auf Übergewicht hin. Achte auf deine Ernährung und bewege dich regelmäßig. Setze dein Ziel auf 'abnehmen', um den idealen Kalorienbedarf für dich von DiaLog ausrechnen zu lassen. Bei einem dauerhaften BMI über 30.0, ist ein Besuch beim Hausarzt zu empfehlen.");
        }

        if(BMI >= 30.0f && user.getWeightGoal().equals("abnehmen")){
            mWeightGoalInfo.setText("Dein BMI weist auf Übergewicht hin. Achte auf deine Ernährung und bewege dich regelmäßig. Bei einem dauerhaften BMI über 30.0, ist ein Besuch beim Hausarzt zu empfehlen.");
        }

        mChangeWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ErnaehrungActivity.class);
                Bundle extra = new Bundle();
                extra.putParcelable("User", user);
                extra.putInt("Fragment", 1);
                intent.putExtras(extra);
                startActivity(intent);
            }
        });

        mAktivitäten.setText("Schlafdauer pro Nacht: " + user.getCalorieDegreeOne() + " h\n\n" +
                                "ausschließlich sitzende/liegende Aktivitäten: " + user.getCalorieDegreeTwo() + " h\n\n" +
                                 "ausschließlich sitzende Tätigkeit mit wenig körperlichen Aktivitäten: " + user.getCalorieDegreeThree() + " h\n\n" +
                                "überwiegend sitzende Aktivitäten mit teilweise Gehen und Stehen: " + user.getCalorieDegreeFour() + " h\n\n" +
                                "überwiegend gehende/stehende \nAktivitäten: " + user.getCalorieDegreeFive() + " h\n\n" +
                                 "körperlich anstrengende Aktivitäten: " + user.getCalorieDegreeSix() + " h\n\n" );

        mChangeAktivität = v.findViewById(R.id.button_aktivität_aktualisieren);
        mChangeAktivität.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AktivitaetActivity.class);
                Bundle extra = new Bundle();
                extra.putParcelable("User", user);
                extra.putInt("Fragment", 1);
                intent.putExtras(extra);
                startActivity(intent);
            }
        });

        mPosts = v.findViewById(R.id.meine_posts);
        mPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), CommentActivity.class);
                Bundle extra = new Bundle();
                extra.putParcelable("User", user);
                extra.putInt("Fragment", 1);
                extra.putInt("PostOrComment", 0);
                intent.putExtras(extra);
                startActivity(intent);

            }
        });





        return v;
    }

}

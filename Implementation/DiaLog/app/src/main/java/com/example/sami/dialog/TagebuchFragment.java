package com.example.sami.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TagebuchFragment extends Fragment {
    private User user;
    private TextView text;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tagebuch, container, false);

        if(getArguments() != null){
            user = getArguments().getParcelable("User");
        }




        text = v.findViewById(R.id.testtext);
        text.setText("ID: " + user.getId() + " \n" +
                "Name: " + user.getFirstname() + " " + user.getName() + "\n" +
                "Geburtstag: " + user.getBirthday() + "\n" +
                "Größe und Gewicht: " + user.getHeight() + " " + user.getWeight() + "\n" +
                "Geschlecht: " + user.getGender() + " \n" +
                "Benutzername: " + user.getNickname() + " \n" +
                "Email: " + user.getEmail() + " \n" +
                "Passwort: " + user.getPassword() + " \n" +
                "Art: " + user.getDmType() + " \n" +
                "Insulin: " + user.getInsulin() + " \n" +
                "Tabletten: " + user.isTablets() + " \n" +
                "Einheiten: " + user.getUnitBZ() + " " + user.getUnitKH() + " \n" +
                "Zielbereich: zwischen " + user.getLowlimit() + "&" + user.getUpperLimit() + " \n" +
                "Unter und Über: " + user.getLowSugar() + " " + user.getUpperSugar()+ " \n" +
                "Faktoren: " + user.getCorrectionFactor() + " " + user.getBeFactor()+ " \n" +
                "Leistungsumsatz " + user.getCalorieDegreeOne()+ " "+ user.getCalorieDegreeTwo() + " " + user.getCalorieDegreeThree() + " " +
                user.getCalorieDegreeFour() + " " + user.getCalorieDegreeFive() + " " + user.getCalorieDegreeSix()  +
                "\n" +
                "Score: " + user.getScore());



        return v;
    }



}

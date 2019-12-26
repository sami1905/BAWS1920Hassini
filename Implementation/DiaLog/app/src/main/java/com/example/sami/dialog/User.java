package com.example.sami.dialog;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class User {

    @SerializedName("name")
    String name;

    @SerializedName("firstname")
    String firstname;

    @SerializedName("nickname")
    String nickname;

    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    @SerializedName("birthday")
    Date birthday;

    @SerializedName("age")
    int age;

    @SerializedName("height")
    int height;

    @SerializedName("weight")
    int weight;

    @SerializedName("dmType")
    boolean dmType;

    @SerializedName("insulin")
    String insulin;

    @SerializedName("unitBZ")
    boolean unitBZ;

    @SerializedName("unitKH")
    String unitKH;

    @SerializedName("boarderDown")
    float boarderDown;

    @SerializedName("boarderUp")
    float boarderUp;

    @SerializedName("targetArea")
    float[] targetArea;

}

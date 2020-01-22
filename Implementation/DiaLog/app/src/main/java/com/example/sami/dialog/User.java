package com.example.sami.dialog;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class User implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("firstname")
    private String firstname;
    @SerializedName("name")
    private String name;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("height")
    private int height;
    @SerializedName("weight")
    private float weight;
    @SerializedName("gender")
    private String gender;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;


    @SerializedName("dmType")
    private String dmType;
    @SerializedName("insulin")
    private String insulin;
    @SerializedName("tablets")
    private boolean tablets;
    @SerializedName("unitBZ")
    private String unitBZ;
    @SerializedName("unitKH")
    private String unitKH;

    @SerializedName("lowLimit")
    private float lowlimit;
    @SerializedName("upperLimit")
    private float upperLimit;
    @SerializedName("lowSugar")
    private float lowSugar;
    @SerializedName("upperSugar")
    private float upperSugar;
    @SerializedName("correctionFactor")
    private float correctionFactor;
    @SerializedName("beFactor")
    private float beFactor;

    @SerializedName("calorieDegreeOne")
    private float calorieDegreeOne;
    @SerializedName("calorieDegreeTwo")
    private float calorieDegreeTwo;
    @SerializedName("calorieDegreeThree")
    private float calorieDegreeThree;
    @SerializedName("calorieDegreeFour")
    private float calorieDegreeFour;
    @SerializedName("calorieDegreeFive")
    private float calorieDegreeFive;
    @SerializedName("calorieDegreeSix")
    private float calorieDegreeSix;
    @SerializedName("weightGoal")
    private String weightGoal;

    @SerializedName("score")
    private int score;

    public User(int id, String firstname, String name, String birthday, int height,
                float weight, String gender, String nickname, String email,
                String password, String dmType, String insulin, boolean tablets,
                String unitBZ, String unitKH, float lowlimit, float upperLimit,
                float lowSugar, float upperSugar, float correctionFactor, float beFactor,
                float calorieDegreeOne, float calorieDegreeTwo, float calorieDegreeThree,
                float calorieDegreeFour, float calorieDegreeFive, float calorieDegreeSix,
                String weightGoal, int score) {
        this.id = id;
        this.firstname = firstname;
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.dmType = dmType;
        this.insulin = insulin;
        this.tablets = tablets;
        this.unitBZ = unitBZ;
        this.unitKH = unitKH;
        this.lowlimit = lowlimit;
        this.upperLimit = upperLimit;
        this.lowSugar = lowSugar;
        this.upperSugar = upperSugar;
        this.correctionFactor = correctionFactor;
        this.beFactor = beFactor;
        this.calorieDegreeOne = calorieDegreeOne;
        this.calorieDegreeTwo = calorieDegreeTwo;
        this.calorieDegreeThree = calorieDegreeThree;
        this.calorieDegreeFour = calorieDegreeFour;
        this.calorieDegreeFive = calorieDegreeFive;
        this.calorieDegreeSix = calorieDegreeSix;
        this.weightGoal = weightGoal;
        this.score = score;
    }

    protected User(Parcel in) {
        id = in.readInt();
        firstname = in.readString();
        name = in.readString();
        birthday = in.readString();
        height = in.readInt();
        weight = in.readFloat();
        gender = in.readString();
        nickname = in.readString();
        email = in.readString();
        password = in.readString();
        dmType = in.readString();
        insulin = in.readString();
        tablets = in.readByte() != 0;
        unitBZ = in.readString();
        unitKH = in.readString();
        lowlimit = in.readFloat();
        upperLimit = in.readFloat();
        lowSugar = in.readFloat();
        upperSugar = in.readFloat();
        correctionFactor = in.readFloat();
        beFactor = in.readFloat();
        calorieDegreeOne = in.readFloat();
        calorieDegreeTwo = in.readFloat();
        calorieDegreeThree = in.readFloat();
        calorieDegreeFour = in.readFloat();
        calorieDegreeFive = in.readFloat();
        calorieDegreeSix = in.readFloat();
        weightGoal = in.readString();
        score = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(firstname);
        dest.writeString(name);
        dest.writeString(birthday);
        dest.writeInt(height);
        dest.writeFloat(weight);
        dest.writeString(gender);
        dest.writeString(nickname);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(dmType);
        dest.writeString(insulin);
        dest.writeByte((byte) (tablets ? 1 : 0));
        dest.writeString(unitBZ);
        dest.writeString(unitKH);
        dest.writeFloat(lowlimit);
        dest.writeFloat(upperLimit);
        dest.writeFloat(lowSugar);
        dest.writeFloat(upperSugar);
        dest.writeFloat(correctionFactor);
        dest.writeFloat(beFactor);
        dest.writeFloat(calorieDegreeOne);
        dest.writeFloat(calorieDegreeTwo);
        dest.writeFloat(calorieDegreeThree);
        dest.writeFloat(calorieDegreeFour);
        dest.writeFloat(calorieDegreeFive);
        dest.writeFloat(calorieDegreeSix);
        dest.writeString(weightGoal);
        dest.writeInt(score);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDmType() {
        return dmType;
    }

    public void setDmType(String dmType) {
        this.dmType = dmType;
    }

    public String getInsulin() {
        return insulin;
    }

    public void setInsulin(String insulin) {
        this.insulin = insulin;
    }

    public boolean isTablets() {
        return tablets;
    }

    public void setTablets(boolean tablets) {
        this.tablets = tablets;
    }

    public String getUnitBZ() {
        return unitBZ;
    }

    public void setUnitBZ(String unitBZ) {
        this.unitBZ = unitBZ;
    }

    public String getUnitKH() {
        return unitKH;
    }

    public void setUnitKH(String unitKH) {
        this.unitKH = unitKH;
    }

    public float getLowlimit() {
        return lowlimit;
    }

    public void setLowlimit(float lowlimit) {
        this.lowlimit = lowlimit;
    }

    public float getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(float upperLimit) {
        this.upperLimit = upperLimit;
    }

    public float getLowSugar() {
        return lowSugar;
    }

    public void setLowSugar(float lowSugar) {
        this.lowSugar = lowSugar;
    }

    public float getUpperSugar() {
        return upperSugar;
    }

    public void setUpperSugar(float upperSugar) {
        this.upperSugar = upperSugar;
    }

    public float getCorrectionFactor() {
        return correctionFactor;
    }

    public void setCorrectionFactor(float correctionFactor) {
        this.correctionFactor = correctionFactor;
    }

    public float getBeFactor() {
        return beFactor;
    }

    public void setBeFactor(float beFactor) {
        this.beFactor = beFactor;
    }

    public float getCalorieDegreeOne() {
        return calorieDegreeOne;
    }

    public void setCalorieDegreeOne(float calorieDegreeOne) {
        this.calorieDegreeOne = calorieDegreeOne;
    }

    public float getCalorieDegreeTwo() {
        return calorieDegreeTwo;
    }

    public void setCalorieDegreeTwo(float calorieDegreeTwo) {
        this.calorieDegreeTwo = calorieDegreeTwo;
    }

    public float getCalorieDegreeThree() {
        return calorieDegreeThree;
    }

    public void setCalorieDegreeThree(float calorieDegreeThree) {
        this.calorieDegreeThree = calorieDegreeThree;
    }

    public float getCalorieDegreeFour() {
        return calorieDegreeFour;
    }

    public void setCalorieDegreeFour(float calorieDegreeFour) {
        this.calorieDegreeFour = calorieDegreeFour;
    }

    public float getCalorieDegreeFive() {
        return calorieDegreeFive;
    }

    public void setCalorieDegreeFive(float calorieDegreeFive) {
        this.calorieDegreeFive = calorieDegreeFive;
    }

    public float getCalorieDegreeSix() {
        return calorieDegreeSix;
    }

    public void setCalorieDegreeSix(float calorieDegreeSix) {
        this.calorieDegreeSix = calorieDegreeSix;
    }

    public String getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(String weightGoal) {
        this.weightGoal = weightGoal;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}

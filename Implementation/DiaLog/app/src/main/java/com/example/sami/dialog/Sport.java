package com.example.sami.dialog;

import android.os.Parcel;
import android.os.Parcelable;

public class Sport implements Parcelable {

    private int id;
    private String date;
    private String time;
    private String descriptions;
    private float duration;
    private int kcal;

    public Sport(int id, String date, String time, String descriptions, float duration, int kcal) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.descriptions = descriptions;
        this.duration = duration;
        this.kcal = kcal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}

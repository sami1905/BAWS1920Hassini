package com.example.sami.dialog;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
    private int id;
    private String date;
    private String time;
    private float sugar;
    private float be;
    private float kie;
    private float ie;

    public Event(int id, String date, String time, float sugar, float be, float kie, float ie) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.sugar = sugar;
        this.be = be;
        this.kie = kie;
        this.ie = ie;
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

    public float getSugar() {
        return sugar;
    }

    public void setSugar(float sugar) {
        this.sugar = sugar;
    }

    public float getBe() {
        return be;
    }

    public void setBe(float be) {
        this.be = be;
    }

    public float getKie() {
        return kie;
    }

    public void setKie(float kie) {
        this.kie = kie;
    }

    public float getIe() {
        return ie;
    }

    public void setIe(float ie) {
        this.ie = ie;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}

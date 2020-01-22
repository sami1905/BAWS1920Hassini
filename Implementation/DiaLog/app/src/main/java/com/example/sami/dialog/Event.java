package com.example.sami.dialog;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
    private int id;
    private int userID;
    private String date;
    private String time;
    private float sugar;
    private float kie;
    private Meal[] meals;
    private float be;
    private float ie;
    private Sport[] sports;

    public Event(int id, int userID, String date, String time, float sugar, float kie, Meal[] meals, float be, float ie, Sport[] sports) {
        this.id = id;
        this.userID = userID;
        this.date = date;
        this.time = time;
        this.sugar = sugar;
        this.kie = kie;
        this.meals = meals;
        this.be = be;
        this.ie = ie;
        this.sports = sports;
    }

    protected Event(Parcel in) {
        id = in.readInt();
        userID = in.readInt();
        date = in.readString();
        time = in.readString();
        sugar = in.readFloat();
        kie = in.readFloat();
        meals = in.createTypedArray(Meal.CREATOR);
        be = in.readFloat();
        ie = in.readFloat();
        sports = in.createTypedArray(Sport.CREATOR);
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public float getSugar() {
        return sugar;
    }

    public float getKie() {
        return kie;
    }

    public Meal[] getMeals() {
        return meals;
    }

    public float getBe() {
        return be;
    }

    public float getIe() {
        return ie;
    }

    public Sport[] getSports() {
        return sports;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSugar(float sugar) {
        this.sugar = sugar;
    }

    public void setKie(float kie) {
        this.kie = kie;
    }

    public void setMeals(Meal[] meals) {
        this.meals = meals;
    }

    public void setBe(float be) {
        this.be = be;
    }

    public void setIe(float ie) {
        this.ie = ie;
    }

    public void setSports(Sport[] sports) {
        this.sports = sports;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(userID);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeFloat(sugar);
        dest.writeFloat(kie);
        dest.writeTypedArray(meals, flags);
        dest.writeFloat(be);
        dest.writeFloat(ie);
        dest.writeTypedArray(sports, flags);
    }
}

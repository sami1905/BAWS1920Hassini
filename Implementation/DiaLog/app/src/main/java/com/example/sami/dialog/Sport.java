package com.example.sami.dialog;

import android.os.Parcel;
import android.os.Parcelable;

public class Sport implements Parcelable {

    private int id;
    private int eventID;
    private String description;
    private float duration;
    private int met;
    private int kcal;

    public Sport(int id, int eventID, String description, float duration, int met, int kcal) {
        this.id = id;
        this.eventID = eventID;
        this.description = description;
        this.duration = duration;
        this.met = met;
        this.kcal = kcal;
    }

    protected Sport(Parcel in) {
        id = in.readInt();
        eventID = in.readInt();
        description = in.readString();
        duration = in.readFloat();
        met = in.readInt();
        kcal = in.readInt();
    }

    public static final Creator<Sport> CREATOR = new Creator<Sport>() {
        @Override
        public Sport createFromParcel(Parcel in) {
            return new Sport(in);
        }

        @Override
        public Sport[] newArray(int size) {
            return new Sport[size];
        }
    };

    public int getId() {
        return id;
    }

    public int getEventID() {
        return eventID;
    }

    public String getDescription() {
        return description;
    }

    public float getDuration() {
        return duration;
    }

    public int getMet() {
        return met;
    }

    public int getKcal() {
        return kcal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public void setMet(int met) {
        this.met = met;
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
        dest.writeInt(id);
        dest.writeInt(eventID);
        dest.writeString(description);
        dest.writeFloat(duration);
        dest.writeInt(met);
        dest.writeInt(kcal);
    }
}

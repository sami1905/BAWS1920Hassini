package com.example.sami.dialog;

import android.os.Parcel;
import android.os.Parcelable;

public class Meal implements Parcelable {

    private int id;
    private int eventID;
    private String description;
    private float amount;
    private float kcal;
    private float e;
    private float kh;
    private float f;
    private String unit;

    public Meal(int id, int eventID, String description, float amount, float kcal, float e, float kh, float f, String unit) {
        this.id = id;
        this.eventID = eventID;
        this.description = description;
        this.amount = amount;
        this.kcal = kcal;
        this.e = e;
        this.kh = kh;
        this.f = f;
        this.unit = unit;
    }

    protected Meal(Parcel in) {
        id = in.readInt();
        eventID = in.readInt();
        description = in.readString();
        amount = in.readFloat();
        kcal = in.readFloat();
        e = in.readFloat();
        kh = in.readFloat();
        f = in.readFloat();
        unit = in.readString();
    }

    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
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

    public float getAmount() {
        return amount;
    }

    public float getKcal() {
        return kcal;
    }

    public float getE() {
        return e;
    }

    public float getKh() {
        return kh;
    }

    public float getF() {
        return f;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public int describeContents() {
        return 0;
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

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setKcal(float kcal) {
        this.kcal = kcal;
    }

    public void setE(float e) {
        this.e = e;
    }

    public void setKh(float kh) {
        this.kh = kh;
    }

    public void setF(float f) {
        this.f = f;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(eventID);
        dest.writeString(description);
        dest.writeFloat(amount);
        dest.writeFloat(kcal);
        dest.writeFloat(e);
        dest.writeFloat(kh);
        dest.writeFloat(f);
        dest.writeString(unit);
    }
}

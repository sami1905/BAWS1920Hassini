package com.example.sami.dialog;

import android.os.Parcel;
import android.os.Parcelable;

public class Meal implements Parcelable {

    private int id;
    private String date;
    private String time;
    private String description;
    private float amount;
    private float kcal;
    private float e;
    private float kh;
    private float f;
    private String unit;

    public Meal(int id, String date, String time, String description, float amount, float kcal, float e, float kh, float f, String unit) {
        this.id = id;
        this.date = date;
        this.time = time;
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
        date = in.readString();
        time = in.readString();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getKcal() {
        return kcal;
    }

    public void setKcal(float kcal) {
        this.kcal = kcal;
    }

    public float getE() {
        return e;
    }

    public void setE(float e) {
        this.e = e;
    }

    public float getKh() {
        return kh;
    }

    public void setKh(float kh) {
        this.kh = kh;
    }

    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(description);
        dest.writeFloat(amount);
        dest.writeFloat(kcal);
        dest.writeFloat(e);
        dest.writeFloat(kh);
        dest.writeFloat(f);
        dest.writeString(unit);
    }
}

package com.example.sami.dialog;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Food implements Parcelable{
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("category")
    private String category;
    @SerializedName("amount")
    private float amount;
    @SerializedName("kcal")
    private float kcal;
    @SerializedName("kj")
    private float kj;
    @SerializedName("e")
    private float e;
    @SerializedName("kh")
    private float kh;
    @SerializedName("f")
    private float f;
    @SerializedName("unit")
    private String unit;

    public Food(int id, String name, String category, float amount, float kcal, float kj, float e, float kh, float f, String unit) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.kcal = kcal;
        this.kj = kj;
        this.e = e;
        this.kh = kh;
        this.f = f;
        this.unit = unit;
    }

    protected Food(Parcel in) {
        id = in.readInt();
        name = in.readString();
        category = in.readString();
        amount = in.readFloat();
        kcal = in.readFloat();
        kj = in.readFloat();
        e = in.readFloat();
        kh = in.readFloat();
        f = in.readFloat();
        unit = in.readString();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public float getKj() {
        return kj;
    }

    public void setKj(float kj) {
        this.kj = kj;
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
        dest.writeString(name);
        dest.writeString(category);
        dest.writeFloat(amount);
        dest.writeFloat(kcal);
        dest.writeFloat(kj);
        dest.writeFloat(e);
        dest.writeFloat(kh);
        dest.writeFloat(f);
        dest.writeString(unit);
    }
}

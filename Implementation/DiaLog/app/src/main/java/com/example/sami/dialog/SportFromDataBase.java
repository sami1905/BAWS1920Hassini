package com.example.sami.dialog;

import android.os.Parcel;
import android.os.Parcelable;

public class SportFromDataBase implements Parcelable {

    private int id;
    private String name;
    private int met;

    public SportFromDataBase(int id, String name, int met) {
        this.id = id;
        this.name = name;
        this.met = met;
    }

    protected SportFromDataBase(Parcel in) {
        id = in.readInt();
        name = in.readString();
        met = in.readInt();
    }

    public static final Creator<SportFromDataBase> CREATOR = new Creator<SportFromDataBase>() {
        @Override
        public SportFromDataBase createFromParcel(Parcel in) {
            return new SportFromDataBase(in);
        }

        @Override
        public SportFromDataBase[] newArray(int size) {
            return new SportFromDataBase[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMet() {
        return met;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(met);
    }
}

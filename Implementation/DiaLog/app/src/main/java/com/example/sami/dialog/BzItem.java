package com.example.sami.dialog;

public class BzItem {
    private int id;
    private int userID;
    private String time;
    private String bz;
    private String bzUnit;
    private String kh;
    private String khUnit;
    private String korrektur;
    private String insulin;

    public BzItem(int id, int userID, String time, String bz, String bzUnit, String kh, String khUnit, String korrektur, String insulin) {
        this.id = id;
        this.userID = userID;
        this.time = time;
        this.bz = bz;
        this.bzUnit = bzUnit;
        this.kh = kh;
        this.khUnit = khUnit;
        this.korrektur = korrektur;
        this.insulin = insulin;
    }

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public String getTime() {
        return time;
    }

    public String getBz() {
        return bz;
    }

    public String getBzUnit() {
        return bzUnit;
    }

    public String getKh() {
        return kh;
    }

    public String getKhUnit() {
        return khUnit;
    }

    public String getKorrektur() {
        return korrektur;
    }

    public String getInsulin() {
        return insulin;
    }
}

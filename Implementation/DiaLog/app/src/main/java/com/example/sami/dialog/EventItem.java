package com.example.sami.dialog;

public class EventItem {

    private int id;
    private int userID;
    private String time;
    private String text;
    private String info;

    public EventItem(int id, int userID, String time, String text, String info) {
        this.id = id;
        this.userID = userID;
        this.time = time;
        this.text = text;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

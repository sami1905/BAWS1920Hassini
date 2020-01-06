package com.example.sami.dialog;

public class Comment {

    private int id;
    private int userID;
    private String userNickname;
    private String text;
    private String date;
    private String time;

    public Comment(int id, int userID, String userNickname, String text, String date, String time) {
        this.id = id;
        this.userID = userID;
        this.userNickname = userNickname;
        this.text = text;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getUserNickname() {
        return userNickname;
    }
}

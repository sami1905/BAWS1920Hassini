package com.example.sami.dialog;

public class Post {

    private int id;
    private int userID;
    private String userNickname;
    private String text;
    private String date;
    private String time;
    private Comment[] comments;

    public Post(int id, int userID, String userNickname, String text, String date, String time, Comment[] comments) {
        this.id = id;
        this.userID = userID;
        this.userNickname = userNickname;
        this.text = text;
        this.date = date;
        this.time = time;
        this.comments = comments;
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

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public Comment[] getComments() {
        return comments;
    }

    public void setComments(Comment[] comments) {
        this.comments = comments;
    }
}

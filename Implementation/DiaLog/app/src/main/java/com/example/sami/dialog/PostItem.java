package com.example.sami.dialog;

public class PostItem {

    private String mNickname;
    private String mText;
    private String mRating;
    private String mAnzahlComment;


    public PostItem(String nickname, String text, String rating, String anzahlComment) {
        mNickname = nickname;
        mText = text;
        mRating = rating;
        mAnzahlComment = anzahlComment;
    }

    public String getmNickname() {
        return mNickname;
    }

    public String getmText() {
        return mText;
    }

    public String getmRating() {
        return mRating;
    }

    public String getmAnzahlComment() {
        return mAnzahlComment;
    }




}

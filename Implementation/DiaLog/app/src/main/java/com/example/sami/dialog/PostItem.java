package com.example.sami.dialog;

public class PostItem {

    private String mNickname;
    private String mText;
    private String mRating;
    private String mAnzahlComment;
    private int mImageResource;
    private int mID;


    public PostItem(String mNickname, String mText, String mRating, String mAnzahlComment, int mImageResource, int mID) {
        this.mNickname = mNickname;
        this.mText = mText;
        this.mRating = mRating;
        this.mAnzahlComment = mAnzahlComment;
        this.mImageResource = mImageResource;
        this.mID = mID;
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

    public int getmImageResource() {
        return mImageResource;
    }

    public int getmID() {
        return mID;
    }
}

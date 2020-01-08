package com.example.sami.dialog;

public class PostItem {

    private String mNickname;
    private String mText;
    private String mAnzahlComment;
    private int mImageResource;
    private int mID;
    private int mUserID;
    private int mDeleteImageResource;
    private int mColorImageResource;


    public PostItem(String mNickname, String mText,  String mAnzahlComment, int mImageResource, int mID, int mUserID, int mDeleteImageResource, int mColorImageResource) {
        this.mNickname = mNickname;
        this.mText = mText;
        this.mAnzahlComment = mAnzahlComment;
        this.mImageResource = mImageResource;
        this.mID = mID;
        this.mUserID = mUserID;
        this.mDeleteImageResource = mDeleteImageResource;
        this.mColorImageResource = mColorImageResource;
    }



    public String getmNickname() {
        return mNickname;
    }

    public String getmText() {
        return mText;
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

    public int getmDeleteImageResource() {
        return mDeleteImageResource;
    }

    public int getmColorImageResource() {
        return mColorImageResource;
    }

    public int getmUserID() {
        return mUserID;
    }
}

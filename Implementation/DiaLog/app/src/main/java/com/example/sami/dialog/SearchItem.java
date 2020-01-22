package com.example.sami.dialog;

public class SearchItem {

    private int id;
    private String text;
    private String info;

    public SearchItem(int id, String text, String info) {
        this.id = id;
        this.text = text;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getInfo() {
        return info;
    }
}

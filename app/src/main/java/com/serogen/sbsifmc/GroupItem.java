package com.serogen.sbsifmc;

public class GroupItem {
    private int imageResourceId;
    private String text;
    private int id;

    public GroupItem(int imageResourceId, String text, int id) {
        this.imageResourceId = imageResourceId;
        this.text = text;
        this.id = id;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }
}
package com.serogen.sbsifmc;

public class InstructionItem {
    private int imageResourceId;
    private String text;

    public InstructionItem(int imageResourceId, String text) {
        this.imageResourceId = imageResourceId;
        this.text = text;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

package com.serogen.sbsifmc;

public class InstructionItemFromInstructionsList {
    private int imageResourceId;
    private String instructionName;
    private boolean mcpeOnly;
    private int id;

    public InstructionItemFromInstructionsList(int imageResourceId, String instructionName, boolean mcpeOnly,int id) {
        this.imageResourceId = imageResourceId;
        this.instructionName = instructionName;
        this.mcpeOnly = mcpeOnly;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getInstructionName() {
        return instructionName;
    }

    public void setInstructionName(String instructionName) {
        this.instructionName = instructionName;
    }

    public boolean isMcpeOnly() {
        return mcpeOnly;
    }

    public void setMcpeOnly(boolean mcpeOnly) {
        this.mcpeOnly = mcpeOnly;
    }
}

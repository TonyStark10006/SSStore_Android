package com.tonystark10006.ssstore.models;

public class Data {

    private String newTitle;
    private String newContent;

    public Data() {}

    public Data(String newTitle, String newContent) {
        this.newTitle = newTitle;
        this.newContent = newContent;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public String getNewContent() {
        return newContent;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public void setNewContent(String newContent) {
        this.newContent = newContent;
    }


}

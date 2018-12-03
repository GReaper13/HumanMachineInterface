package com.example.greaper.drone.data;

public class Image {
    int marginTop, marginLeft, type;

    public Image(int marginTop, int marginLeft, int type) {
        this.marginTop = marginTop;
        this.marginLeft = marginLeft;
        this.type = type;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

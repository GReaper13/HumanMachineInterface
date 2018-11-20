package com.example.greaper.drone.data;

public class TotalItem {
    private String date;
    private int type;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public TotalItem(String date, int type) {
        this.date = date;
        this.type = type;
    }
}

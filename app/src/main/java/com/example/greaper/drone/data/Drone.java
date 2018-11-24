package com.example.greaper.drone.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Drone {

    private int id;

    private String name;

    public Drone() {
    }

    public Drone(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

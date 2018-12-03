package com.example.greaper.drone.utils;

public class AppUtils {
    public static String intToTime(long time) {
        int timeInInt = (int)(time / 1000);
        int hours = timeInInt / 3600;
        int minute = (timeInInt - hours * 3600) / 60;
        int seconds = timeInInt - (minute * 60 + hours * 3600);
        String result = "";
        if (hours < 10) {
            result += "0" + hours;
        } else {
            result += hours;
        }
        result += ":";
        if (minute < 10) {
            result += "0" + minute;
        } else {
            result += minute;
        }
        result += ":";
        if (seconds < 10) {
            result += "0" + seconds;
        } else {
            result += seconds;
        }
        return result;
    }
}

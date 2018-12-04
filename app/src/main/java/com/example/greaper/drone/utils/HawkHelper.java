package com.example.greaper.drone.utils;

import com.orhanobut.hawk.Hawk;

public class HawkHelper {
    private static final String IMG_ROUTE = "route";
    public static String getImageRoute() {
         return Hawk.get(IMG_ROUTE, "");
    }
    public static void setImageRoute(String base64) {
        Hawk.put(IMG_ROUTE, base64);
    }
}

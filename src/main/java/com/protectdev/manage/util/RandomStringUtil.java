package com.protectdev.manage.util;

public class RandomStringUtil {

    public static String getRandom(){

        return Integer.toString ((int)((Math.random()*9+1)*100000));
    }

}

package com.wyy.qgcloud.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.wyy.qgcloud.app.MyApplication;

public class SharedPerencesUtil {

    public static void saveLength(String file,int length){
        SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("data",Context.MODE_PRIVATE).edit();
        editor.putInt(file,length);
        editor.apply();
    }

    public static int getLength(String file){
        SharedPreferences preferences = MyApplication.getContext().getSharedPreferences("data",Context.MODE_PRIVATE);
        return preferences.getInt(file,0);
    }
}

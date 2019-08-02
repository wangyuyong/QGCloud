package com.wyy.qgcloud.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.wyy.qgcloud.app.MyApplication;

public class SharedPerencesUtil {

    public static void saveLength(String file,long length){
        SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("data",Context.MODE_PRIVATE).edit();
        editor.putLong(file,length);
        editor.apply();
    }

    public static long getLength(String file){
        SharedPreferences preferences = MyApplication.getContext().getSharedPreferences("data",Context.MODE_PRIVATE);

        return preferences.getLong(file,0);
    }

    public static void deleteLength(String file){
        SharedPreferences.Editor preferences = MyApplication.getContext().getSharedPreferences("data",Context.MODE_PRIVATE).edit();
        preferences.remove(file);
    }
}

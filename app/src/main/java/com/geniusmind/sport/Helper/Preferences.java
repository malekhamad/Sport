package com.geniusmind.sport.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
   // set preferences as string . . . ;
    public static void setPreferences(Context context , String pref_file ,String key ,String value){
        SharedPreferences setting = context.getSharedPreferences(pref_file,0);
        SharedPreferences.Editor editor = setting.edit();
        editor.putString(key,value);
        editor.apply();
    }

    // set prefereces as int . . . ;
    public static void setPreferences(Context context , String pref_file , String key , int value){
        SharedPreferences setting = context.getSharedPreferences(pref_file,0);
        SharedPreferences.Editor editor = setting.edit();
        editor.putInt(key,value);
        editor.apply();
    }

    // set preferences as boolean . . . ;
    public static void setPreferences(Context context, String pref_file , String key, boolean value){
        SharedPreferences setting = context.getSharedPreferences(pref_file,0);
        SharedPreferences.Editor editor = setting.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }

    // get preferences as String . . . ;
    public static String getPreferences(Context context,String pref_file,String key ,String def_value){
      SharedPreferences setting = context.getSharedPreferences(pref_file,0);
      return setting.getString(key,def_value);
    }

    // get preference as integer . . . . ;
    public static int getPreferences(Context context,String pref_file ,String key , int def_value){
        SharedPreferences setting =context.getSharedPreferences(pref_file,0);
        return setting.getInt(key,def_value);
    }

    // get preference as boolean . . . ;
    public static boolean getPreferences(Context context , String pref_file , String key , boolean def_value){
        SharedPreferences setting = context.getSharedPreferences(pref_file,0);
        return setting.getBoolean(key,def_value);
    }



}

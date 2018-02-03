package jsbankagent.management.application.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Administrator on 1/3/2018.
 */

public class DataHandler {


    private static Context context;
    public static String FILE_NAME_SHARED_PREF = "JSBankAgentManagementSystem";

    public DataHandler(Context context) {
        DataHandler.context = context;
    }

    public static void setContext(Context context) {

        DataHandler.context = context;
    }

    public static void updatePreferences(String key, Long value) {
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME_SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        editor.commit();

    }
    public static void updatePreferences(String key, JSONArray value) {
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME_SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value.toString());
        editor.commit();

    }
    public static Long getLongreferences(String key) {
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME_SHARED_PREF,  Context.MODE_PRIVATE);
        return  settings.getLong(key,0);

    }
    public static void updatePreferences(String key, Boolean value) {
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME_SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.commit();

    }
    public static void updatePreferences(String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME_SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.commit();

    }
    public static void updatePreferences(String key, double value) {
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME_SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, Double.doubleToRawLongBits(value));
        editor.commit();

    }

    public static void updatePreferences(String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME_SHARED_PREF, Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();

    }

    public static Boolean getBooleanPreferences(String key) {
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME_SHARED_PREF, 0);
        return settings.getBoolean(key, false);

    }

    public static void deletePreference(String key) {
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME_SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.commit();

    }
    public static Double getDoublePreference(String key) {
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME_SHARED_PREF, 0);

        return Double.longBitsToDouble(settings.getLong(key, Double.doubleToLongBits(0l)));
    }

    public static int getIntPreferences(String key) {
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME_SHARED_PREF, 0);
        return settings.getInt(key, -1);
    }
    // Preference for Passenger Mode
    public static int getIntPreferencesPassengerMode(String key) {
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME_SHARED_PREF, 0);
        return settings.getInt(key, 0);
    }
    public static String getStringPreferences(String key) {
        SharedPreferences settings = context.getSharedPreferences(FILE_NAME_SHARED_PREF,  Context.MODE_PRIVATE);
        return settings.getString(key, "");

    }



}

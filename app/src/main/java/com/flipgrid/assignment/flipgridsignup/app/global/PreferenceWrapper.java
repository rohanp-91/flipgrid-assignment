package com.flipgrid.assignment.flipgridsignup.app.global;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class PreferenceWrapper {
    public static final String SHARED_PREFERENCES_NAME = "0";
    private final SharedPreferences sharedPreferences;

    PreferenceWrapper(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor getEditor() {
        return sharedPreferences.edit();
    }

    public String readString(String key) {
        try {
            return sharedPreferences.getString(key, null);
        } catch (Exception e) {
            Log.e("DataStore", "readString failed with key " + key, e);
            return null;
        }
    }

    public boolean readBoolean(String key, boolean defaultValue) {
        try {
            return sharedPreferences.getBoolean(key, defaultValue);
        } catch (Exception e) {
            Log.e("DataStore", "readBoolean failed with key " + key, e);
            return false;
        }
    }

    public int readInt(String key, int defaultValue) {
        try {
            return sharedPreferences.getInt(key, defaultValue);
        } catch (Exception e) {
            Log.e("DataStore", "readInt failed with key " + key, e);
            return 0;
        }
    }

    public long readLong(String key, long defaultValue) {
        try {
            return sharedPreferences.getLong(key, defaultValue);
        } catch (Exception e) {
            Log.e("DataStore", "readLong failed with key " + key, e);
            return 0;
        }
    }

    public void writeString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void writeBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void writeInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void writeLong(String key, long value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }
}

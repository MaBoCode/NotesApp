package fr.notes.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    public static final String PREFS_FILE = "fr.notes";
    public static final String PREFS_ENABLE_DARK_MODE = "PREFS_ENABLE_DARK_MODE";

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
    }

    public static boolean getPrefEnableDarkMode(Context context) {
        return getSharedPreferences(context).getBoolean(PREFS_ENABLE_DARK_MODE, false);
    }

    public static void setPrefEnableDarkMode(Context context, boolean enableDarkMode) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(Prefs.PREFS_ENABLE_DARK_MODE, enableDarkMode);
        editor.apply();
    }

}

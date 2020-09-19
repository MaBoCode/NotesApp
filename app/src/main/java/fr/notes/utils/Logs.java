package fr.notes.utils;

import android.util.Log;

public class Logs {

    public static String tag = "[MDB_LOG]";

    public enum Type {
        VERBOSE("VERBOSE"),
        DEBUG("DEBUG"),
        INFO("INFO"),
        WARN("WARN"),
        ERROR("ERROR");

        private String type;

        Type(String type) {
            this.type = type;
        }
    }

    public static void debug(Object objectClass, String message) {
        Log.d("[" + Type.DEBUG.type + "]", message);
    }

}

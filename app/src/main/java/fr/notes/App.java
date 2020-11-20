package fr.notes;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

import dagger.hilt.android.HiltAndroidApp;
import fr.notes.utils.AppThemeUtils;
import fr.notes.utils.Prefs;

@HiltAndroidApp
public class App extends Application {

    private static WeakReference<Context> contextWeakReference;

    @Override
    public void onCreate() {
        super.onCreate();

        contextWeakReference = new WeakReference<>(getApplicationContext());

        setAppTheme();
    }

    public static Context getAppContext() {
        return contextWeakReference.get();
    }

    public void setAppTheme() {
        AppThemeUtils.enableDarkMode(Prefs.getPrefEnableDarkMode(contextWeakReference.get()));
    }
}

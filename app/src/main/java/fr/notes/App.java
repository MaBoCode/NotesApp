package fr.notes;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

import fr.notes.injects.AppComponent;
import fr.notes.injects.DaggerAppComponent;
import fr.notes.injects.modules.MainModule;
import fr.notes.injects.modules.WebServicesModule;
import fr.notes.utils.AppThemeUtils;
import fr.notes.utils.Prefs;

public class App extends Application {

    private static WeakReference<Context> contextWeakReference;
    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        contextWeakReference = new WeakReference<>(getApplicationContext());

        appComponent = DaggerAppComponent.builder()
                .mainModule(new MainModule())
                .webServicesModule(new WebServicesModule(contextWeakReference.get()))
                .build();

        setAppTheme();
    }

    public static Context getAppContext() {
        return contextWeakReference.get();
    }

    public void setAppTheme() {
        AppThemeUtils.enableDarkMode(Prefs.getPrefEnableDarkMode(contextWeakReference.get()));
    }
}

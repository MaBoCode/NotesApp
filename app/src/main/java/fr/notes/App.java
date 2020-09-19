package fr.notes;

import android.app.Application;
import android.content.Context;

import fr.notes.injects.AppComponent;
import fr.notes.injects.DaggerAppComponent;

public class App extends Application {

    public static Context context;
    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        appComponent = DaggerAppComponent.create();
    }

    public static Context getAppContext() {
        return context;
    }
}

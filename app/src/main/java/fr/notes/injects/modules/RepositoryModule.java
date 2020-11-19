package fr.notes.injects.modules;

import android.content.Context;

import dagger.Module;

@Module
public class RepositoryModule {

    private Context context;

    public RepositoryModule(Context context) {
        this.context = context;
    }
}

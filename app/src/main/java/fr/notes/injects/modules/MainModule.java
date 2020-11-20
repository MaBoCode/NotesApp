package fr.notes.injects.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import fr.notes.injects.bus.AppBus;
import fr.notes.injects.bus.AppBusOtto;

@Module
@InstallIn(ApplicationComponent.class)
public class MainModule {

    @Singleton
    @Provides
    public AppBus provideBus() {
        return new AppBusOtto();
    }
}

package fr.notes.injects.modules;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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

    @Singleton
    @Provides
    public Executor provideExecutor() {
        return Executors.newFixedThreadPool(1);
    }
}

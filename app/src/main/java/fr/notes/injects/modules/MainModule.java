package fr.notes.injects.modules;

import dagger.Module;
import dagger.Provides;
import fr.notes.injects.bus.AppBus;
import fr.notes.injects.bus.AppBusOtto;

@Module
public class MainModule {

    @Provides
    public AppBus provideBus() {
        return AppBusOtto.getInstance();
    }
}

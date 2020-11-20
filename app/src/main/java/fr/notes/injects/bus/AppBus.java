package fr.notes.injects.bus;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@EntryPoint
@InstallIn(SingletonComponent.class)
public interface AppBus {

    void register(Object o);

    void unregister(Object o);

    void post(final Object event);
}

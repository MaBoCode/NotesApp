package fr.notes.injects.bus;

public interface AppBus {

    void register(Object o);

    void unregister(Object o);

    void post(final Object event);
}

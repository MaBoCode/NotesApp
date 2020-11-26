package fr.notes.injects.base;

import androidx.lifecycle.ViewModel;

import fr.notes.injects.bus.AppBus;

public abstract class BaseViewModel extends ViewModel {

    public boolean isRegistered = false;

    public void register(AppBus bus) {
        if (!isRegistered) {
            bus.register(this);
            isRegistered = true;
        }
    }

    public void unregister(AppBus bus) {
        if (isRegistered) {
            bus.unregister(this);
            isRegistered = false;
        }
    }

}

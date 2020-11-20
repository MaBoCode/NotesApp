package fr.notes.injects.bus;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

import fr.notes.utils.Logs;

public class AppBusOtto implements AppBus {

    private final Handler mainLooper = new Handler(Looper.getMainLooper());

    private Bus bus;

    public AppBusOtto() {
        this.bus = new Bus();
    }

    @Override
    public void register(Object o) {
        bus.register(o);
    }

    @Override
    public void unregister(Object o) {
        bus.unregister(o);
    }

    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            bus.post(event);
        } else {
            mainLooper.post(new Runnable() {
                @Override
                public void run() {
                    post(event);
                }
            });
        }
    }
}

package fr.notes.injects.bus;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

public class AppBusOtto implements AppBus {

    private final Handler mainLooper = new Handler(Looper.getMainLooper());

    private static AppBusOtto instance = null;
    private static Bus bus = null;

    public AppBusOtto() {
        bus = new Bus();
    }

    public static AppBusOtto getInstance() {
        if (instance == null) {
            instance = new AppBusOtto();
        }
        return instance;
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

package fr.notes.injects.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import fr.notes.R;
import fr.notes.injects.bus.AppBus;

public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    protected AppBus bus;

    protected Context appContext;
    protected Activity uiContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.appContext = getApplicationContext();
        this.uiContext = this;

        inject();
    }

    public abstract void inject();

    @Override
    protected void onStart() {
        super.onStart();
        bus.register(this);
    }

    @Override
    protected void onStop() {
        bus.unregister(this);
        super.onStop();
    }
}

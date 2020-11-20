package fr.notes.injects.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import javax.inject.Inject;

import dagger.hilt.EntryPoints;
import dagger.hilt.android.AndroidEntryPoint;
import fr.notes.R;
import fr.notes.injects.bus.AppBus;

public abstract class BaseActivity extends FragmentActivity {

    @Inject
    protected AppBus bus;

    protected Context appContext;
    protected Activity uiContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.appContext = getApplicationContext();
        this.uiContext = this;
    }

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

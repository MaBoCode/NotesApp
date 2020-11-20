package fr.notes.injects.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.androidannotations.annotations.EFragment;

import javax.inject.Inject;

import dagger.hilt.EntryPoints;
import dagger.hilt.android.AndroidEntryPoint;
import fr.notes.App;
import fr.notes.injects.bus.AppBus;

public abstract class BaseFragment extends Fragment {

    @Inject
    protected AppBus bus;

    protected Context appContext;
    protected Context uiContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appContext = App.getAppContext();
        uiContext = this.getActivity();

        bus.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        bus.unregister(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        appContext = null;
        uiContext = null;
    }

    public boolean onBackPressed() {
        return false;
    }
}

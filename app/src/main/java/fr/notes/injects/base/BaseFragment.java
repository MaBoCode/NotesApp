package fr.notes.injects.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.androidannotations.annotations.EFragment;

import javax.inject.Inject;

import fr.notes.App;
import fr.notes.injects.bus.AppBus;

@EFragment
public abstract class BaseFragment extends Fragment {

    @Inject
    protected AppBus bus;

    protected Context appContext;
    protected Context uiContext;

    public abstract void inject();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appContext = App.getAppContext();
        uiContext = this.getActivity();

        inject();

        bus.register(this);

        setTransitions();
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

    public abstract void setTransitions();
}

package fr.notes;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;
import fr.notes.injects.base.BaseViewModel;
import fr.notes.injects.bus.AppBus;
import fr.notes.utils.AppThemeUtils;
import fr.notes.utils.Prefs;

@HiltAndroidApp
public class App extends Application {

    @Inject
    protected AppBus bus;

    private static WeakReference<Context> contextWeakReference;
    private static List<ViewModel> viewModels = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        contextWeakReference = new WeakReference<>(getApplicationContext());

        setAppTheme();
    }

    public static Context getAppContext() {
        return contextWeakReference.get();
    }

    public void setAppTheme() {
        AppThemeUtils.enableDarkMode(Prefs.getPrefEnableDarkMode(contextWeakReference.get()));
    }

    public static <T extends ViewModel> T getViewModel(ViewModelStoreOwner owner, Class<T> tClass) {
        T viewModel = new ViewModelProvider(owner).get(tClass);

        if (!viewModels.contains(viewModel)) {
            viewModels.add(viewModel);
        }
        return viewModel;
    }

    public void unregisterViewModels() {
        for (ViewModel viewModel: viewModels) {
            ((BaseViewModel) viewModel).unregister(bus);
        }
    }

    @Override
    public void onTerminate() {
        unregisterViewModels();

        super.onTerminate();
    }
}

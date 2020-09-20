package fr.notes.views;

import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.otto.Subscribe;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

import fr.notes.App;
import fr.notes.R;
import fr.notes.injects.base.BaseActivity;
import fr.notes.injects.bus.AppBus;
import fr.notes.utils.AppThemeUtils;
import fr.notes.utils.Logs;
import fr.notes.utils.Prefs;
import fr.notes.views.events.ShowFragmentEvent;

@EActivity(R.layout.act_main)
public class MainActivity extends BaseActivity {

    @Inject
    protected AppBus bus;
    @Inject
    protected LoginViewModel loginViewModel;

    @ViewById
    protected MaterialToolbar tlbHome;
    @ViewById
    protected SwitchCompat swtTheme;

    protected Bundle lastSavedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        lastSavedInstanceState = savedInstanceState;
    }

    @Override
    public void inject() {
        ((App) appContext).appComponent.inject(this);
    }

    @AfterViews
    public void bind() {

        swtTheme.setChecked(Prefs.getPrefEnableDarkMode(appContext));

        displayFirstFragment();
    }

    public void displayFirstFragment() {

        if (lastSavedInstanceState == null) {
            ShowFragmentEvent event = new ShowFragmentEvent(MainFragment_.builder().build());
            event.replace = false;
            event.addToBackStack = true;
            showFragment(event);
        }
    }

    @Subscribe
    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void showFragment(ShowFragmentEvent event) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (event.replace) {
            transaction.replace(R.id.fragment_container, event.fragment);
        } else {
            transaction.add(R.id.fragment_container, event.fragment);
        }

        if (event.addToBackStack) {
            transaction.addToBackStack(event.fragmentName);
        } else {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    @CheckedChange(R.id.swtTheme)
    public void switchTheme(boolean isChecked) {
        Logs.debug(this, "[DEBUG] switch");

        Prefs.setPrefEnableDarkMode(appContext, isChecked);

        AppThemeUtils.enableDarkMode(isChecked);
    }
}
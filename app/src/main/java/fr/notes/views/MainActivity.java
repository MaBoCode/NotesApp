package fr.notes.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.squareup.otto.Subscribe;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

import fr.notes.App;
import fr.notes.R;
import fr.notes.injects.base.BaseActivity;
import fr.notes.injects.bus.AppBus;
import fr.notes.utils.Logs;
import fr.notes.views.events.ShowFragmentEvent;

@EActivity(R.layout.act_main)
public class MainActivity extends BaseActivity {

    @Inject
    protected AppBus bus;
    @Inject
    protected LoginViewModel loginViewModel;

    @ViewById
    protected Toolbar tlbMain;

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

        setSupportActionBar(tlbMain);

        getSupportActionBar().setTitle("Notes");
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        displayFirstFragment();
    }

    public void displayFirstFragment() {

        if (lastSavedInstanceState == null) {
            ShowFragmentEvent event = new ShowFragmentEvent(MainFragment_.builder().build());
            event.replace = true;
            event.addToBackStack = true;
            showFragment(event);
        }
    }

    @Subscribe
    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void showFragment(ShowFragmentEvent event) {

        Logs.debug(this, "[DEBUG] here");

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
}
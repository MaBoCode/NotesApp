package fr.notes.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.squareup.otto.Subscribe;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import java.util.LinkedList;

import javax.inject.Inject;

import fr.notes.App;
import fr.notes.R;
import fr.notes.injects.base.BaseActivity;
import fr.notes.injects.base.BaseFragment;
import fr.notes.injects.bus.AppBus;
import fr.notes.utils.Logs;
import fr.notes.views.events.PopFragmentEvent;
import fr.notes.views.events.ShowFragmentEvent;

@EActivity(R.layout.act_main)
public class MainActivity extends BaseActivity {

    @Inject
    protected AppBus bus;
    @Inject
    protected LoginViewModel loginViewModel;

    protected Bundle lastSavedInstanceState;

    private LinkedList<Fragment> fragments;

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

        displayFirstFragment();
    }

    public void displayFirstFragment() {
        fragments = new LinkedList<>();

        if (lastSavedInstanceState == null) {

            ShowFragmentEvent event = new ShowFragmentEvent(MainFragment_.builder().build());
            event.replace = true;
            event.addToBackStack = true;
            showFragment(event);
        } else {
            Logs.debug(this, "[DEBUG] not first instance state: " + fragments.size());
            fragments = new LinkedList<>(getSupportFragmentManager().getFragments());
        }
    }

    @Subscribe
    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void showFragment(ShowFragmentEvent event) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (!fragments.isEmpty()) {
            fragments.getLast().onPause();
        }

        if (event.animations == null) {
            transaction.setCustomAnimations(
                    R.anim.slide_enter,
                    R.anim.slide_exit,
                    R.anim.slide_pop_enter,
                    R.anim.slide_pop_exit
            );
        }

        if (event.replace) {
            transaction.replace(R.id.fragment_container, event.fragment);
        } else {
            transaction.add(R.id.fragment_container, event.fragment);
        }

        if (event.addToBackStack) {
            transaction.addToBackStack(event.fragmentName);
            fragments.addLast(event.fragment);
        } else {
            transaction.addToBackStack(null);
        }

        if (event.animations != null) {
            Logs.debug(this, String.format("%s:%s", event.animations[0], R.anim.slide_enter));
            transaction.setCustomAnimations(event.animations[0], event.animations[1]);
        }

        transaction.commit();
    }

    @Subscribe
    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void popFragment(PopFragmentEvent event) {
        if (fragments.size() > 1) {
            fragments.removeLast();
            getSupportFragmentManager().popBackStackImmediate();

            if (!fragments.isEmpty()) {
                fragments.getLast().onResume();
            }
        }
    }

    @Override
    public void onBackPressed() {
        Logs.debug(this, "[DEBUG] fragments: " + fragments.size());

        if (!lastFragmentOnBackPressed()) {
            if (fragments.size() > 1) {
                popFragment(new PopFragmentEvent());
            }
        } else {
            Logs.debug(this, "[DEBUG] finish");
            finish();
        }
    }

    public boolean lastFragmentOnBackPressed() {
        if (!fragments.isEmpty()) {
            Fragment lastFragment = fragments.getLast();
            if (lastFragment instanceof BaseFragment) {
                return ((BaseFragment) lastFragment).onBackPressed();
            }
        }
        return false;
    }
}
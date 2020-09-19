package fr.notes.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.transition.TransitionInflater;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import fr.notes.App;
import fr.notes.R;
import fr.notes.injects.base.BaseFragment;
import fr.notes.models.NoteModel;
import fr.notes.views.events.ShowFragmentEvent;
import fr.notes.views.notes.NoteDetailsFragment_;
import fr.notes.views.notes.NotesListView;

@EFragment(R.layout.frg_main)
@OptionsMenu(R.menu.menu_more_options)
public class MainFragment extends BaseFragment {

    @ViewById
    protected NotesListView viewNotes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TransitionInflater inflater = TransitionInflater.from(uiContext);
        setEnterTransition(inflater.inflateTransition(R.transition.slide_right));
    }

    @AfterViews
    public void init() {

        List<NoteModel> notes = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            notes.add(new NoteModel("Note " + i, "Some content", "September 16"));
        }

        viewNotes.bind(notes);
    }

    @Override
    public void inject() {
        ((App) appContext).appComponent.inject(this);
    }

    @Override
    public void setTransitions() {
        TransitionInflater inflater = TransitionInflater.from(uiContext);
        setEnterTransition(inflater.inflateTransition(R.transition.fade));
    }

    @Click(R.id.btnNewNote)
    public void addNewNote() {
        ShowFragmentEvent event = new ShowFragmentEvent(NoteDetailsFragment_.builder().build());
        event.replace = true;
        event.addToBackStack = true;
        bus.post(event);
    }

    @OptionsItem(R.id.itmSetLightTheme)
    public void setLightTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    @OptionsItem(R.id.itmSetDarkTheme)
    public void setDarkTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }
}

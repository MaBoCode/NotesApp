package fr.notes.views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.transition.TransitionInflater;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

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
public class MainFragment extends BaseFragment {

    @ViewById
    protected NotesListView viewNotes;

    @StringRes(R.string.default_note_content)
    String default_note_content;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TransitionInflater inflater = TransitionInflater.from(uiContext);
        setEnterTransition(inflater.inflateTransition(R.transition.slide_right));
    }

    @AfterViews
    public void init() {
        display();
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

    public void display() {
        List<NoteModel> notes = new ArrayList<>();

        notes.add(new NoteModel("Tasks", default_note_content, "Sun, 8:00"));
        notes.add(new NoteModel("Balance", "Some content", "Sun, 8:00"));
        notes.add(new NoteModel("Tasks", default_note_content, "Sun, 8:00"));
        notes.add(new NoteModel("Tasks", "Some content", "Sun, 8:00"));
        notes.add(new NoteModel("Tasks", default_note_content, "Sun, 8:00"));
        notes.add(new NoteModel("Tasks", default_note_content, "Sun, 8:00"));
        notes.add(new NoteModel("Balance", "Some content", "Sun, 8:00"));
        notes.add(new NoteModel("Tasks", default_note_content, "Sun, 8:00"));
        notes.add(new NoteModel("Tasks", "Some content", "Sun, 8:00"));
        notes.add(new NoteModel("Tasks", default_note_content, "Sun, 8:00"));
        notes.add(new NoteModel("Tasks", default_note_content, "Sun, 8:00"));
        notes.add(new NoteModel("Balance", "Some content", "Sun, 8:00"));
        notes.add(new NoteModel("Tasks", default_note_content, "Sun, 8:00"));
        notes.add(new NoteModel("Tasks", "Some content", "Sun, 8:00"));
        notes.add(new NoteModel("Tasks", default_note_content, "Sun, 8:00"));

        if (!isDetached()) {
            viewNotes.bind(notes);
        }
    }
}

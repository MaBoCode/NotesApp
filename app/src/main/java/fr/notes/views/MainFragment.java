package fr.notes.views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import fr.notes.App;
import fr.notes.R;
import fr.notes.core.note.Note;
import fr.notes.core.note.webservices.NoteClient;
import fr.notes.core.note.webservices.NoteClientCallback;
import fr.notes.injects.base.BaseFragment;
import fr.notes.models.NoteModel;
import fr.notes.utils.AppThemeUtils;
import fr.notes.utils.Logs;
import fr.notes.utils.Prefs;
import fr.notes.views.events.ShowFragmentEvent;
import fr.notes.views.notes.NoteDetailsFragment_;
import fr.notes.views.notes.NotesListView;

@EFragment(R.layout.frg_main)
public class MainFragment extends BaseFragment {

    @Inject
    protected NoteClient noteClient;

    @ViewById
    protected Toolbar tlbMain;
    @ViewById
    protected SwitchCompat swtTheme;

    @ViewById
    protected NotesListView viewNotes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void init() {

        tlbMain.setTitle(getString(R.string.app_name));
        display();
    }

    @Override
    public void inject() {
        ((App) appContext).appComponent.inject(this);
    }

    @Override
    public void setTransitions() {

    }

    @Click(R.id.btnNewNote)
    public void addNewNote() {
        ShowFragmentEvent event = new ShowFragmentEvent(NoteDetailsFragment_.builder().build());
        event.replace = true;
        event.addToBackStack = true;

        bus.post(event);
    }

    public void display() {
        if (!isDetached()) {
            noteClient.loadNotes(new NoteClientCallback<List<Note>>() {
                @Override
                public void success(List<Note> notes) {
                    viewNotes.bind(notes);
                }

                @Override
                public void failure(Throwable throwable) {
                    Logs.error(this, "Failed loading notes: " + throwable.getMessage());
                }
            });

        }
    }

    @CheckedChange(R.id.swtTheme)
    public void switchTheme(boolean isChecked) {
        Logs.debug(this, "[checked]" + isChecked);

        Prefs.setPrefEnableDarkMode(appContext, isChecked);

        AppThemeUtils.enableDarkMode(isChecked);
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }
}

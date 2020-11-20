package fr.notes.views;

import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import fr.notes.R;
import fr.notes.core.events.GetNotesStateEvent;
import fr.notes.core.note.Note;
import fr.notes.core.note.NoteViewModel;
import fr.notes.core.util.DataState;
import fr.notes.injects.base.BaseFragment;
import fr.notes.utils.AppThemeUtils;
import fr.notes.utils.Logs;
import fr.notes.utils.Prefs;
import fr.notes.views.events.ShowFragmentEvent;
import fr.notes.views.notes.NoteDetailsFragment_;
import fr.notes.views.notes.NotesListView;

@AndroidEntryPoint
@EFragment(R.layout.frg_main)
public class MainFragment extends BaseFragment {

    protected NoteViewModel noteViewModel;

    @ViewById
    protected Toolbar tlbMain;
    @ViewById
    protected SwitchCompat swtTheme;

    @ViewById
    protected NotesListView viewNotes;

    @AfterViews
    public void init() {
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        subscribeObservers();
        noteViewModel.setStateEvent(new GetNotesStateEvent());
        tlbMain.setTitle(getString(R.string.app_name));
        display();
    }

    public void subscribeObservers() {
        if (noteViewModel != null) {
            noteViewModel.getDataState().observe(this, new Observer<DataState<List<Note>>>() {
                @Override
                public void onChanged(DataState<List<Note>> listDataState) {
                    if (listDataState instanceof DataState.Success) {
                        Logs.debug(this, "[DEBUG] success");
                    } else {
                        Logs.debug(this, "[DEBUG] error");
                    }
                }
            });
        }
    }

    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void display() {

    }

    @Click(R.id.btnNewNote)
    public void addNewNote() {
        ShowFragmentEvent event = new ShowFragmentEvent(NoteDetailsFragment_.builder().build());
        event.replace = true;
        event.addToBackStack = true;

        bus.post(event);
    }

    @CheckedChange(R.id.swtTheme)
    public void switchTheme(boolean isChecked) {

        Prefs.setPrefEnableDarkMode(appContext, isChecked);

        AppThemeUtils.enableDarkMode(isChecked);
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }
}

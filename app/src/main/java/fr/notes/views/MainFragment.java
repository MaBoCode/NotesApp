package fr.notes.views;

import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import fr.notes.App;
import fr.notes.R;
import fr.notes.core.events.FetchNotesStateEvent;
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
        noteViewModel = App.getViewModel(requireActivity(), NoteViewModel.class);
        noteViewModel.register(bus);
        subscribeObservers();
        bus.post(new FetchNotesStateEvent());
        tlbMain.setTitle(getString(R.string.app_name));
    }

    public void subscribeObservers() {
        if (noteViewModel != null) {
            noteViewModel.getDataState().observe(this, new Observer<DataState<List<Note>>>() {
                @Override
                public void onChanged(DataState<List<Note>> listDataState) {
                    if (listDataState instanceof DataState.Success) {
                        viewNotes.bind(((DataState.Success<List<Note>>) listDataState).data);
                    } else {
                        Logs.error(this, "datastate: " + ((DataState.Error<List<Note>>) listDataState).exception);
                    }
                }
            });
        }
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

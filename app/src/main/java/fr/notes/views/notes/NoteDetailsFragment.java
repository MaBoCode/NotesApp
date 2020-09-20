package fr.notes.views.notes;

import android.widget.EditText;
import android.widget.TextView;

import androidx.transition.TransitionInflater;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import fr.notes.App;
import fr.notes.R;
import fr.notes.injects.base.BaseFragment;
import fr.notes.models.NoteModel;

@EFragment(R.layout.frg_note_details)
@OptionsMenu(R.menu.menu_empty)
public class NoteDetailsFragment extends BaseFragment {

    @FragmentArg
    protected NoteModel note;

    @ViewById
    protected TextView txtNoteTitle;
    @ViewById
    protected TextView txtNoteDate;
    @ViewById
    protected EditText edtNoteContent;

    @Override
    public void inject() {
        ((App) appContext).appComponent.inject(this);
    }

    @AfterViews
    public void init() {



        if (note != null) {
            display(note);
        }
    }

    @Override
    public void setTransitions() {
        TransitionInflater inflater = TransitionInflater.from(uiContext);
        setEnterTransition(inflater.inflateTransition(R.transition.slide_right));
    }

    public void display(NoteModel note) {
        txtNoteTitle.setText(note.getNoteTitle());
        txtNoteDate.setText(note.getNoteTimeStamp());
        edtNoteContent.setText(note.getNoteContent());
    }
}

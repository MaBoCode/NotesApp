package fr.notes.views.notes;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;

import dagger.hilt.android.AndroidEntryPoint;
import fr.notes.R;
import fr.notes.core.note.Note;
import fr.notes.core.note.NoteViewModel;
import fr.notes.injects.base.BaseFragment;

@AndroidEntryPoint
@EFragment(R.layout.frg_note_details)
@OptionsMenu(R.menu.menu_empty)
public class NoteDetailsFragment extends BaseFragment {

    @FragmentArg
    protected Note note;
    @FragmentArg
    protected boolean editMode = false;

    @ViewById
    protected Toolbar tlbMain;
    @ViewById
    protected EditText edtNoteTitle;
    @ViewById
    protected AutoCompleteTextView txtNoteCategories;
    @ViewById
    protected ChipGroup grpChipCategories;
    @ViewById
    protected TextView txtNoteDate;
    @ViewById
    protected EditText edtNoteContent;

    protected boolean textChanged = false;

    @AfterViews
    public void init() {
        tlbMain.setTitle("");

        //((FragmentActivity) getActivity()).setSupportActionBar(tlbMain);
        //((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        display(note);
    }

    public void display(Note note) {
        if (note == null) {
            return;
        }

        edtNoteTitle.setText(note.title);
        edtNoteContent.setText(note.content);
        //TODO: Decrease title font size if line > 1
    }

    @EditorAction(R.id.txtNoteCategories)
    public void categoriesTextViewEdited(TextView tv, int actionId) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            String category = tv.getText().toString();
            tv.setText(null);
            addTag(category);
        }
    }

    public void addTag(String category) {
        Chip chip = new Chip(uiContext);
        chip.setText(category);
        chip.setClickable(true);
        chip.setCheckable(true);
        chip.setCloseIconVisible(true);

        Animation animation = AnimationUtils.loadAnimation(uiContext, R.anim.bounce_in);
        chip.setAnimation(animation);

        grpChipCategories.addView(chip);

        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grpChipCategories.removeView(chip);
            }
        });
    }

    @TextChange({R.id.edtNoteTitle, R.id.edtNoteContent})
    void onTextChanged() {
        textChanged = true;
    }

    @Override
    public boolean onBackPressed() {

        if (textChanged) {
            String noteTitle = edtNoteTitle.getText().toString();
            String noteContent = edtNoteContent.getText().toString();
            if (editMode) {
                //noteViewModel.editNote(note.id, noteTitle, noteContent);
            } else {
                //noteViewModel.saveNote(noteTitle, noteContent);
            }
        }

        return super.onBackPressed();
    }
}

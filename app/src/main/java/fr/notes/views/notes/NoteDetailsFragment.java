package fr.notes.views.notes;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import fr.notes.App;
import fr.notes.R;
import fr.notes.core.note.Note;
import fr.notes.injects.base.BaseFragment;

@EFragment(R.layout.frg_note_details)
@OptionsMenu(R.menu.menu_empty)
public class NoteDetailsFragment extends BaseFragment {

    @FragmentArg
    protected Note note;

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

    @Override
    public void inject() {
        ((App) appContext).appComponent.inject(this);
    }

    @AfterViews
    public void init() {
        tlbMain.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(tlbMain);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (note != null) {
            display(note);
        }
    }

    @Override
    public void setTransitions() {

    }

    public void display(Note note) {
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
}

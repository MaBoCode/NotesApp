package fr.notes.views.notes;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import fr.notes.App;
import fr.notes.R;
import fr.notes.injects.base.BaseConstraintLayout;
import fr.notes.models.NoteModel;
import fr.notes.views.notes.adapters.NotesRecycleAdapter;

@EViewGroup(R.layout.view_notes_list)
public class NotesListView extends BaseConstraintLayout {

    @ViewById
    protected RecyclerView lstNotes;

    private List<NoteModel> notes = new ArrayList<>();

    private LinearLayoutManager layoutManager;
    private NotesRecycleAdapter notesRecycleAdapter;

    public NotesListView(Context context) {
        super(context);
    }

    public NotesListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NotesListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void inject() {
        ((App) App.getAppContext()).appComponent.inject(this);
    }

    @AfterViews
    public void init() {
        if (!isInEditMode()) {
            display();
        }
    }

    public void display() {

        if (layoutManager == null) {
            layoutManager = new LinearLayoutManager(uiContext);
            lstNotes.setLayoutManager(layoutManager);
        }

        if (notesRecycleAdapter == null) {
            notesRecycleAdapter = new NotesRecycleAdapter(uiContext, notes);
            lstNotes.setAdapter(notesRecycleAdapter);
        } else {
            notesRecycleAdapter.notifyDataSetChanged();
        }
    }

    public void bind(List<NoteModel> notes) {
        if (notes != null) {
            this.notes.clear();
            this.notes.addAll(notes);
        }
    }
}

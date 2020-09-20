package fr.notes.views.notes;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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
    private GridLayoutManager gridLayoutManager;

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

        if (gridLayoutManager == null) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            lstNotes.setLayoutManager(staggeredGridLayoutManager);
        }

        NotesRecycleAdapter notesRecycleAdapter = new NotesRecycleAdapter(uiContext, notes);
        lstNotes.setAdapter(notesRecycleAdapter);
    }

    public void bind(List<NoteModel> notes) {
        this.notes = notes;
        display();
    }
}

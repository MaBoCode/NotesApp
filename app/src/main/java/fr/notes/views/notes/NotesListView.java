package fr.notes.views.notes;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import fr.notes.App;
import fr.notes.R;
import fr.notes.injects.base.BaseConstraintLayout;
import fr.notes.models.NoteModel;
import fr.notes.utils.Logs;
import fr.notes.views.notes.adapters.NotesCardViewRecycleAdapter;

@EViewGroup(R.layout.view_notes_list)
public class NotesListView extends BaseConstraintLayout {

    @ViewById
    protected RecyclerView lstNotes;

    private List<NoteModel> notes = new ArrayList<>();

    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    protected boolean isDisplaying = false;

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

    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void display() {

        if (notes != null) {
            lstNotes.removeAllViews();
            lstNotes.setVisibility(notes.isEmpty() ? GONE : VISIBLE);

            if (staggeredGridLayoutManager == null) {
                staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                lstNotes.setLayoutManager(staggeredGridLayoutManager);
            }

            NotesCardViewRecycleAdapter notesCardViewRecycleAdapter = new NotesCardViewRecycleAdapter(uiContext, notes);
            lstNotes.setAdapter(notesCardViewRecycleAdapter);

            isDisplaying = true;
        }
    }

    public void bind(List<NoteModel> notes) {
        Logs.debug(this, "");
        if (notes != null) {
            this.notes = notes;
            display();
            //TODO: notifyDataSetChanged
        }
    }
}

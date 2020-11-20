package fr.notes.views.notes;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.squareup.otto.Subscribe;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import fr.notes.R;
import fr.notes.core.note.Note;
import fr.notes.injects.base.BaseConstraintLayout;
import fr.notes.utils.Logs;
import fr.notes.views.notes.adapters.NotesCardViewRecycleAdapter;
import fr.notes.views.notes.events.NoteCardDeselectedEvent;
import fr.notes.views.notes.events.NoteCardSelectedEvent;

@EViewGroup(R.layout.view_notes_list)
public class NotesListView extends BaseConstraintLayout {

    @ViewById
    protected RecyclerView lstNotes;

    protected NotesCardViewRecycleAdapter notesCardViewRecycleAdapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    protected int selectedCards = 0;

    protected List<Note> notes = new ArrayList<>();

    public NotesListView(Context context) {
        super(context);
    }

    public NotesListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NotesListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void display() {

        lstNotes.removeAllViews();

        if (staggeredGridLayoutManager == null) {
            staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            lstNotes.setLayoutManager(staggeredGridLayoutManager);
        }

        notesCardViewRecycleAdapter = new NotesCardViewRecycleAdapter(uiContext, notes);
        lstNotes.setAdapter(notesCardViewRecycleAdapter);
    }

    public void bind(List<Note> notes) {
        updateDataSet(notes);
        if (notesCardViewRecycleAdapter == null) {
            display();
        } else {
            notesCardViewRecycleAdapter.setItems(this.notes);
            notesCardViewRecycleAdapter.notifyDataSetChanged();
        }
    }

    public void updateDataSet(List<Note> notes) {
        this.notes.clear();
        this.notes.addAll(notes);
    }

    @Subscribe
    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void cardSelectedEvent(NoteCardSelectedEvent event) {
        selectedCards++;
        setAllCardsToClickToSelect(true);
    }

    @Subscribe
    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void cardDeselectedEvent(NoteCardDeselectedEvent event) {
        selectedCards--;
        if (selectedCards == 0) {
            setAllCardsToClickToSelect(false);
        }
    }

    public void setAllCardsToClickToSelect(boolean clickToSelect) {

        for (int i = 0; i < lstNotes.getChildCount(); i++) {
            NoteCardView cardView = (NoteCardView) lstNotes.getChildAt(i);
            cardView.setClickToSelect(clickToSelect);
        }
    }
}

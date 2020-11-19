package fr.notes.views.notes.adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import fr.notes.models.NoteModel;
import fr.notes.views.base.BaseRecyclerViewAdapter;
import fr.notes.views.base.RecyclerViewWrapper;
import fr.notes.views.notes.NoteCardView;
import fr.notes.views.notes.NoteCardView_;

public class NotesCardViewRecycleAdapter extends BaseRecyclerViewAdapter<NoteModel, NoteCardView> {

    private Context context;

    public NotesCardViewRecycleAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    public NotesCardViewRecycleAdapter(Context context, List<NoteModel> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    protected NoteCardView onCreateItemView(ViewGroup parent, int viewType) {
        return NoteCardView_.build(context);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewWrapper<NoteCardView> holder, int position) {
        NoteCardView noteCardView = holder.getView();
        NoteModel noteModel = items.get(position);
        noteCardView.setNoteModel(noteModel);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}

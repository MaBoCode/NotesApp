package fr.notes.views.notes.adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.notes.models.NoteModel;
import fr.notes.views.base.BaseRecyclerViewAdapter;
import fr.notes.views.base.RecyclerViewWrapper;
import fr.notes.views.notes.NoteCardView;
import fr.notes.views.notes.NoteCardView_;

public class NotesRecycleAdapter extends BaseRecyclerViewAdapter<NoteModel, NoteCardView> {

    private Context context;

    public NotesRecycleAdapter(Context context) {
        this.context = context;
    }

    public NotesRecycleAdapter(Context context, List<NoteModel> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    protected NoteCardView onCreateItemView(ViewGroup parent, int viewType) {
        return NoteCardView_.build(context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewWrapper<NoteCardView> holder, int position) {
        NoteCardView noteCardView = holder.getView();
        NoteModel noteModel = items.get(position);

        noteCardView.setNoteModel(noteModel);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }
}

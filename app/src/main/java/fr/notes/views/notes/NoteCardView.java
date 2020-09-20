package fr.notes.views.notes;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.LongClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import fr.notes.App;
import fr.notes.R;
import fr.notes.injects.base.BaseFrameLayout;
import fr.notes.models.NoteModel;
import fr.notes.utils.Logs;
import fr.notes.views.events.ShowFragmentEvent;

@EViewGroup(R.layout.view_note)
public class NoteCardView extends BaseFrameLayout {

    @ViewById
    protected CardView viewNoteCard;
    @ViewById
    protected TextView txtNoteTitle;
    @ViewById
    protected TextView txtNoteDate;

    protected NoteModel noteModel;

    protected boolean isSelected = false;

    public NoteCardView(@NonNull Context context) {
        super(context);
    }

    public NoteCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NoteCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NoteCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void inject() {
        ((App) App.getAppContext()).appComponent.inject(this);
    }

    @AfterViews
    public void init() {
        display();
    }

    @UiThread
    public void display() {

        if (noteModel != null) {
            txtNoteTitle.setText(noteModel.getNoteTitle());
            txtNoteDate.setText(noteModel.getNoteTimeStamp());
        }
    }

    @Click(R.id.viewNoteCard)
    public void onCardClick() {
        ShowFragmentEvent event = new ShowFragmentEvent(NoteDetailsFragment_.builder().note(noteModel).build());
        event.replace = true;
        event.addToBackStack = true;
        bus.post(event);
    }

    @LongClick(R.id.viewNoteCard)
    public void onCardLongClick() {

    }


    public void setNoteModel(NoteModel noteModel) {
        if (noteModel != null) {
            this.noteModel = noteModel;
            display();
        }

    }
}

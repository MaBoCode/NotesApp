package fr.notes.views.notes;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.LongClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.Random;

import fr.notes.App;
import fr.notes.R;
import fr.notes.injects.base.BaseFrameLayout;
import fr.notes.models.NoteModel;
import fr.notes.utils.DimUtils;
import fr.notes.utils.Logs;
import fr.notes.views.events.ShowFragmentEvent;

@EViewGroup(R.layout.view_note)
public class NoteCardView extends BaseFrameLayout {

    @ViewById
    protected CardView viewNoteCard;
    @ViewById
    protected TextView txtNoteTitle;
    @ViewById
    protected TextView txtNoteContent;
    @ViewById
    protected TextView txtNoteDate;

    protected NoteModel noteModel;

    protected Integer maxLines;

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

    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void display() {

        if (noteModel != null) {
            if (maxLines == null) {
                maxLines = getRandomIntInRange(5, 15);
                txtNoteContent.setMaxLines(maxLines);
            }
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

    public Integer getRandomIntInRange(Integer min, Integer max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public void setNoteModel(NoteModel noteModel) {
        if (noteModel != null) {
            this.noteModel = noteModel;
            display();
        }

    }
}

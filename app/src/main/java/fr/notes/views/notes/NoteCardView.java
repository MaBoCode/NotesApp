package fr.notes.views.notes;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.card.MaterialCardView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.LongClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fr.notes.R;
import fr.notes.core.note.Note;
import fr.notes.injects.base.BaseFrameLayout;
import fr.notes.utils.Logs;
import fr.notes.views.events.ShowFragmentEvent;
import fr.notes.views.notes.events.NoteCardDeselectedEvent;
import fr.notes.views.notes.events.NoteCardSelectedEvent;

@EViewGroup(R.layout.view_note)
public class NoteCardView extends BaseFrameLayout {

    @ViewById
    protected MaterialCardView viewNoteCard;
    @ViewById
    protected TextView txtNoteTitle;
    @ViewById
    protected TextView txtNoteContent;
    @ViewById
    protected TextView txtNoteDate;

    protected Note note;

    protected boolean clickToSelect = false;

    public NoteCardView(@NonNull Context context) {
        super(context);
    }

    public NoteCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NoteCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @AfterViews
    public void init() {
        display();
    }

    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void display() {

        if (note != null) {
            txtNoteTitle.setText(note.title);
            txtNoteContent.setText(note.content);
            txtNoteContent.setMaxLines(10);

            if (!note.dateAndTime.isEmpty()) {
                try {
                    DateFormat dbFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy, h:mm a", Locale.getDefault());
                    DateFormat finalFormat = new SimpleDateFormat("MMM. d", Locale.getDefault());
                    Date date = dbFormat.parse(note.dateAndTime);
                    txtNoteDate.setText(finalFormat.format(date));
                } catch (ParseException e) {
                    Logs.error(this, e.getMessage());
                }
            }
        }
    }

    @Click(R.id.viewNoteCard)
    public void onCardClick() {

        if (clickToSelect) {
            boolean isChecked = viewNoteCard.isChecked();

            viewNoteCard.setChecked(!isChecked);

            if (isChecked) {
                bus.post(new NoteCardDeselectedEvent());
            } else {
                bus.post(new NoteCardSelectedEvent());
            }
        } else {

            ShowFragmentEvent event = new ShowFragmentEvent(NoteDetailsFragment_.builder().note(note).editMode(true).build());
            event.replace = true;
            event.addToBackStack = true;
            bus.post(event);
        }
    }

    @LongClick(R.id.viewNoteCard)
    public void onCardLongClick() {
        boolean isChecked = viewNoteCard.isChecked();

        viewNoteCard.setChecked(!isChecked);

        if (isChecked) {
            bus.post(new NoteCardDeselectedEvent());
        } else {
            bus.post(new NoteCardSelectedEvent());
        }
    }

    public void setClickToSelect(boolean clickToSelect) {
        this.clickToSelect = clickToSelect;
    }

    public void setNote(Note note) {
        if (note != null) {
            this.note = note;
            display();
        }
    }
}

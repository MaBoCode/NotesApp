package fr.notes.core.events;

import java.util.Date;

public class EditNoteStateEvent extends BaseStateEvent {
    public Long noteId;
    public String title;
    public String content;
}

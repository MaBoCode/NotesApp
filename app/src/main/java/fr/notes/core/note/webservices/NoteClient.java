package fr.notes.core.note.webservices;

import java.util.List;

import fr.notes.core.note.Note;

public interface NoteClient {

    void loadNotes(NoteClientCallback<List<Note>> callback);
}

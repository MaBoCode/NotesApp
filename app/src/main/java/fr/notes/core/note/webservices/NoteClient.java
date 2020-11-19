package fr.notes.core.note.webservices;

import java.util.List;

import fr.notes.core.note.Note;

public interface NoteClient {

    void loadNotes(NoteClientCallback<List<Note>> callback);

    void saveNote(String title, String content, NoteClientCallback<Note> callback);

    void editNote(Long noteId, String title, String content, NoteClientCallback<Note> callback);
}

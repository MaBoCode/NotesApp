package fr.notes.core.note.webservices;

import java.util.List;

import dagger.hilt.android.scopes.FragmentScoped;
import fr.notes.core.note.Note;

public interface NoteClient {

    void loadNotes(Long userId, NoteClientCallback<List<Note>> callback);

    void saveNote(Long userId, String title, String content, NoteClientCallback<Note> callback);

    void editNote(Long userId, Long noteId, String title, String content, NoteClientCallback<Note> callback);
}

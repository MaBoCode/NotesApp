package fr.notes.core.note.repositories;

import java.util.List;

import fr.notes.core.note.Note;
import fr.notes.core.note.webservices.NoteService;
import retrofit2.Call;

public class NoteRepository {

    protected final NoteService noteService;

    public NoteRepository(NoteService noteService) {
        this.noteService = noteService;
    }

    public Call<List<Note>> getNotes(Long userId) {
        return noteService.getNotes(userId);
    }
}

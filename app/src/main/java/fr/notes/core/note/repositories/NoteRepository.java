package fr.notes.core.note.repositories;

import java.util.List;

import fr.notes.core.note.Note;
import fr.notes.core.note.webservices.NoteClient;
import fr.notes.core.note.webservices.NoteService;
import retrofit2.Call;

public class NoteRepository {

    protected final NoteClient noteClient;

    public NoteRepository(NoteClient noteClient) {
        this.noteClient = noteClient;
    }

    public Call<List<Note>> getNotes(Long userId) {
        return null;
    }
}

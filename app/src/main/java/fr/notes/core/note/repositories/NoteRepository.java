package fr.notes.core.note.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import fr.notes.core.note.Note;
import fr.notes.core.note.cache.NoteDao;
import fr.notes.core.note.util.NoteCacheEntityMapper;
import fr.notes.core.note.util.NoteEntityMapper;
import fr.notes.core.note.webservices.NoteService;

public class NoteRepository {

    protected NoteService noteService;
    protected NoteDao noteDao;
    protected NoteCacheEntityMapper cacheEntityMapper;
    protected NoteEntityMapper entityMapper;

    protected MutableLiveData<List<Note>> notes = new MutableLiveData<>();
    protected MutableLiveData<Note> note = new MutableLiveData<>();

    public NoteRepository(NoteDao noteDao, NoteService noteService, NoteCacheEntityMapper cacheEntityMapper, NoteEntityMapper entityMapper) {
        this.noteService = noteService;
        this.noteDao = noteDao;
        this.cacheEntityMapper = cacheEntityMapper;
        this.entityMapper = entityMapper;
    }

    public MutableLiveData<List<Note>> fetchNotes(Long userId) {

        return notes;
    }

    public LiveData<Note> saveNote(Long userId, String title, String content) {

        return note;
    }

    public void editNote(Long userId, Long noteId,  String title, String content) {

    }
}

package fr.notes.core.note.repositories;

import java.util.List;

import fr.notes.core.note.Note;
import fr.notes.core.note.cache.NoteCacheEntity;
import fr.notes.core.note.cache.NoteDao;
import fr.notes.core.note.util.NoteCacheEntityMapper;
import fr.notes.core.note.util.NoteEntityMapper;
import fr.notes.core.note.webservices.NoteEntity;
import fr.notes.core.note.webservices.NoteService;
import fr.notes.core.util.DataState;

public class NoteRepository {

    protected NoteDao noteDao;
    protected NoteService noteService;
    protected NoteCacheEntityMapper cacheEntityMapper;
    protected NoteEntityMapper entityMapper;

    public NoteRepository(NoteDao noteDao, NoteService noteService, NoteCacheEntityMapper cacheEntityMapper, NoteEntityMapper entityMapper) {
        this.noteDao = noteDao;
        this.noteService = noteService;
        this.cacheEntityMapper = cacheEntityMapper;
        this.entityMapper = entityMapper;
    }

    public DataState<List<Note>> fetchNotes(Long userId) {
        try {
            List<NoteEntity> entities = noteService.getNotes(userId);
            List<Note> notes = entityMapper.mapFromEntities(entities);

            for (Note note: notes) {
                NoteCacheEntity cacheEntity = cacheEntityMapper.mapToEntity(note);
                noteDao.insert(cacheEntity);
            }

            List<NoteCacheEntity> cachedNotes = noteDao.getAll();
            notes = cacheEntityMapper.mapFromEntities(cachedNotes);
            return new DataState.Success<>(notes);
        } catch (Exception e) {
            return new DataState.Error<>(e);
        }
    }

}

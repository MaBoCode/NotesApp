package fr.notes.core.note.repositories;

import java.util.List;
import java.util.concurrent.Executor;

import fr.notes.core.note.Note;
import fr.notes.core.note.NoteRequest;
import fr.notes.core.note.cache.NoteCacheEntity;
import fr.notes.core.note.cache.NoteDao;
import fr.notes.core.note.util.NoteCacheEntityMapper;
import fr.notes.core.note.util.NoteEntityMapper;
import fr.notes.core.note.webservices.NoteEntity;
import fr.notes.core.note.webservices.NoteService;
import fr.notes.core.util.DataState;
import fr.notes.utils.Logs;
import retrofit2.Response;

public class NoteRepository {

    protected NoteDao noteDao;
    protected NoteService noteService;
    protected NoteCacheEntityMapper cacheEntityMapper;
    protected NoteEntityMapper entityMapper;

    protected final Executor executor;

    public interface NoteRepositoryCallback<D> {
        void onComplete(DataState<D> result);
    }

    public NoteRepository(NoteDao noteDao, NoteService noteService,
                          NoteCacheEntityMapper cacheEntityMapper, NoteEntityMapper entityMapper,
                          Executor executor) {
        this.noteDao = noteDao;
        this.noteService = noteService;
        this.cacheEntityMapper = cacheEntityMapper;
        this.entityMapper = entityMapper;
        this.executor = executor;
    }

    public void fetchNotes(Long userId, NoteRepositoryCallback<List<Note>> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<NoteEntity> entities = noteService.getNotes(userId).execute().body();

                    if (entities == null) {
                        return;
                    }

                    List<Note> notes = entityMapper.mapFromEntities(entities);

                    for (Note note: notes) {
                        NoteCacheEntity cacheEntity = cacheEntityMapper.mapToEntity(note);
                        noteDao.insert(cacheEntity);
                    }

                    List<NoteCacheEntity> cachedNotes = noteDao.getAll();
                    notes = cacheEntityMapper.mapFromEntities(cachedNotes);

                    DataState<List<Note>> dataState = new DataState.Success<>(notes);
                    callback.onComplete(dataState);
                } catch (Exception e) {
                    DataState<List<Note>> dataState = new DataState.Error<>(e);
                    callback.onComplete(dataState);
                }
            }
        });
    }

    public void addNote(Long userId, String title, String content, NoteRepositoryCallback<List<Note>> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    NoteRequest request = new NoteRequest();
                    request.title = title;
                    request.content = content;

                    Response<NoteEntity> res = noteService.addNote(userId, request).execute();
                    NoteEntity entity = res.body();

                    if (entity == null) {
                        Logs.error(this, "entity: " + res.errorBody().string());
                        return;
                    }

                    Note note = entityMapper.mapFromEntity(entity);
                    NoteCacheEntity cacheEntity = cacheEntityMapper.mapToEntity(note);
                    noteDao.insert(cacheEntity);

                    List<NoteCacheEntity> cachedEntities = noteDao.getAll();
                    List<Note> notes = cacheEntityMapper.mapFromEntities(cachedEntities);

                    DataState<List<Note>> dataState = new DataState.Success<>(notes);
                    callback.onComplete(dataState);
                } catch (Exception e) {
                    DataState<List<Note>> dataState = new DataState.Error<>(e);
                    callback.onComplete(dataState);
                }
            }
        });
    }

    public void editNote(Long userId, Long noteId, NoteRequest request, NoteRepositoryCallback<List<Note>> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    NoteEntity entity = noteService.editNote(userId, noteId, request).execute().body();
                    Note note = entityMapper.mapFromEntity(entity);
                    NoteCacheEntity cacheEntity = cacheEntityMapper.mapToEntity(note);
                    noteDao.updateNote(cacheEntity);

                    List<NoteCacheEntity> cachedEntities = noteDao.getAll();
                    List<Note> notes = cacheEntityMapper.mapFromEntities(cachedEntities);

                    DataState<List<Note>> dataState = new DataState.Success<>(notes);
                    callback.onComplete(dataState);
                } catch (Exception e) {
                    DataState<List<Note>> dataState = new DataState.Error<>(e);
                    callback.onComplete(dataState);
                }
            }
        });
    }

}

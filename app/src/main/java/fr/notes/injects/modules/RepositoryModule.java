package fr.notes.injects.modules;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import fr.notes.core.note.cache.NoteDao;
import fr.notes.core.note.repositories.NoteRepository;
import fr.notes.core.note.util.NoteCacheEntityMapper;
import fr.notes.core.note.util.NoteEntityMapper;
import fr.notes.core.note.webservices.NoteService;

@Module
@InstallIn(ApplicationComponent.class)
public class RepositoryModule {

    @Provides
    @Singleton
    public NoteRepository provideNoteRepository(
            NoteDao noteDao, NoteService noteService,
            NoteCacheEntityMapper cacheEntityMapper, NoteEntityMapper entityMapper,
            Executor executor) {
        return new NoteRepository(noteDao, noteService, cacheEntityMapper, entityMapper, executor);
    }
}

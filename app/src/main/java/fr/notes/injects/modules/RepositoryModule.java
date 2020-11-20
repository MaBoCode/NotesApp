package fr.notes.injects.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import fr.notes.core.note.repositories.NoteRepository;
import fr.notes.core.note.webservices.NoteClient;
import fr.notes.core.note.webservices.NoteService;

@Module
@InstallIn(ApplicationComponent.class)
public class RepositoryModule {

    @Provides
    @Singleton
    public NoteRepository provideNoteRepository(NoteClient noteClient) {
        return new NoteRepository(noteClient);
    }
}

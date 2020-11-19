package fr.notes.injects.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.notes.core.note.repositories.NoteRepository;

@Module
public class RepositoryModule {

    private Context context;

    public RepositoryModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public NoteRepository provideNoteRepository() {
        return null;
    }
}

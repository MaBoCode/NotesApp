package fr.notes.injects.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import fr.notes.core.note.NoteViewModel;
import fr.notes.core.note.repositories.NoteRepository;

@Module
@InstallIn(ApplicationComponent.class)
public class ViewModelModule {

    @Provides
    @Singleton
    public NoteViewModel provideNoteViewModel(NoteRepository noteRepository) {
        return new NoteViewModel(noteRepository);
    }

}

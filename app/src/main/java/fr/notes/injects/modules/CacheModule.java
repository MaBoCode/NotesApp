package fr.notes.injects.modules;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import fr.notes.core.note.cache.NoteDao;
import fr.notes.core.note.cache.NoteDatabase;

@Module
@InstallIn(ApplicationComponent.class)
public class CacheModule {

    @Singleton
    @Provides
    public NoteDatabase provideNoteDatabase(@ApplicationContext Context context) {
        return Room
                .databaseBuilder(
                        context,
                        NoteDatabase.class,
                        NoteDatabase.DB_NAME
                )
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    public NoteDao provideNoteDao(NoteDatabase noteDatabase) {
        return noteDatabase.noteDao();
    }

}

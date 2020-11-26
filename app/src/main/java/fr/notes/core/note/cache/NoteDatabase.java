package fr.notes.core.note.cache;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import fr.notes.utils.DateTimeConverter;

@Database(entities = {NoteCacheEntity.class}, version = 3, exportSchema = false)
@TypeConverters({DateTimeConverter.class})
public abstract class NoteDatabase extends RoomDatabase {

    public final static String DB_NAME = "notes_db";

    public abstract NoteDao noteDao();
}

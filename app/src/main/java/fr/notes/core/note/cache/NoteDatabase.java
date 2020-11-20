package fr.notes.core.note.cache;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {NoteCacheEntity.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    public final static String DB_NAME = "notes_db";

    public abstract NoteDao noteDao();
}

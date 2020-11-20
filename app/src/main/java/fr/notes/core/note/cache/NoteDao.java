package fr.notes.core.note.cache;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM notes")
    List<NoteCacheEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(NoteCacheEntity entity);

}

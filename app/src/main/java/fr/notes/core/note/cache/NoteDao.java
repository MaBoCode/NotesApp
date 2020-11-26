package fr.notes.core.note.cache;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM notes")
    List<NoteCacheEntity> getAll();

    @Query("SELECT * FROM notes WHERE id = :noteId")
    NoteCacheEntity getNote(Long noteId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NoteCacheEntity entity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateNote(NoteCacheEntity entity);

}

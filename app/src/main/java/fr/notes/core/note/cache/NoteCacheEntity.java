package fr.notes.core.note.cache;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import fr.notes.utils.DateTimeConverter;

@Entity(tableName = "notes")
public class NoteCacheEntity {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    public Long id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "content")
    public String content;

    @ColumnInfo(name = "dateAndTime")
    @TypeConverters({DateTimeConverter.class})
    public String dateAndTime;

    public NoteCacheEntity(Long id, String title, String content, String dateAndTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateAndTime = dateAndTime;
    }
}

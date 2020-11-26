package fr.notes.core.note;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Note implements Serializable {

    public Long id;
    public String title;
    public String content;
    public String dateAndTime;

    public Note(Long id, String title, String content, String dateAndTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", dateAndTime='" + dateAndTime + '\'' +
                '}';
    }
}

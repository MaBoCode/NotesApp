package fr.notes.core.note;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Note implements Serializable {

    public Long id;
    public String title;
    public String content;

    public Note(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

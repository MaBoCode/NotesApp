package fr.notes.core.note;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Note implements Serializable {

    public String id;
    public String title;
    public String content;

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

package fr.notes.core.note;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Note implements Serializable {

    public String id;
    public String title;
    public String content;
    public String timestamp;
    public LocalDateTime created;
    public Integer lineCount;

    public Integer countContentLines() {
        int lineCount = 0;
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c == '\n')
                lineCount++;
        }
        return lineCount;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", creationDate=" + created +
                ", lineCount=" + lineCount +
                '}';
    }
}

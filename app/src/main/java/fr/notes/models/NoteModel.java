package fr.notes.models;

import java.io.Serializable;
import java.util.Date;

public class NoteModel implements Serializable {

    private String noteTitle;
    private String noteContent;
    private String noteTimeStamp;
    private Date noteCreationDate;
    private Integer lineCount;

    public NoteModel(String noteTitle, String noteContent, String noteTimeStamp) {
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        this.noteTimeStamp = noteTimeStamp;
        this.lineCount = countContentLines();
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public String getNoteTimeStamp() {
        return noteTimeStamp;
    }

    public Integer getLineCount() {
        return lineCount;
    }

    public Integer countContentLines() {
        int lineCount = 0;
        for (int i = 0; i < noteContent.length(); i++) {
            char c = noteContent.charAt(i);
            if (c == '\n')
                lineCount++;
        }
        return lineCount;
    }
}

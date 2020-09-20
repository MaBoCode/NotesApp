package fr.notes.models;

import java.io.Serializable;
import java.util.Date;

public class NoteModel implements Serializable {

    private String noteTitle;
    private String noteContent;
    private String noteTimeStamp;
    private Date noteCreationDate;

    public NoteModel(String noteTitle, String noteContent, String noteTimeStamp) {
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        this.noteTimeStamp = noteTimeStamp;
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
}

package fr.notes.models;

import java.io.Serializable;

public class NoteModel implements Serializable {

    private String noteTitle;
    private String noteContent;
    private String noteTimeStamp;

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

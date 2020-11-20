package fr.notes.core.note.webservices;

import com.google.gson.annotations.SerializedName;

public class NoteEntity {

    @SerializedName("id")
    public Long id;

    @SerializedName("title")
    public String title;

    @SerializedName("content")
    public String content;

    public NoteEntity(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}

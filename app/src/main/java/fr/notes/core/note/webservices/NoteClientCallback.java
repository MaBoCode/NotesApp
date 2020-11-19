package fr.notes.core.note.webservices;

public interface NoteClientCallback<T> {

    void success(T object);

    void failure(Throwable throwable);
}

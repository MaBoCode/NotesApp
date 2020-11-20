package fr.notes.core.note.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import fr.notes.core.note.Note;
import fr.notes.core.note.webservices.NoteClient;
import fr.notes.core.note.webservices.NoteClientCallback;
import fr.notes.utils.Logs;

public class NoteRepository {

    protected final NoteClient noteClient;

    protected MutableLiveData<List<Note>> notes = new MutableLiveData<>();
    protected MutableLiveData<Note> note = new MutableLiveData<>();

    public NoteRepository(NoteClient noteClient) {
        this.noteClient = noteClient;
    }

    public MutableLiveData<List<Note>> fetchNotes(Long userId) {
        noteClient.loadNotes(userId, new NoteClientCallback<List<Note>>() {
            @Override
            public void success(List<Note> object) {
                notes.setValue(object);
            }

            @Override
            public void failure(Throwable throwable) {

            }
        });
        return notes;
    }

    public LiveData<Note> saveNote(Long userId, String title, String content) {
        noteClient.saveNote(userId, title, content, new NoteClientCallback<Note>() {
            @Override
            public void success(Note object) {
                note.setValue(object);
            }

            @Override
            public void failure(Throwable throwable) {

            }
        });
        return note;
    }

    public void editNote(Long userId, Long noteId,  String title, String content) {
        noteClient.editNote(userId, noteId, title, content, new NoteClientCallback<Note>() {
            @Override
            public void success(Note object) {

            }

            @Override
            public void failure(Throwable throwable) {

            }
        });
    }
}

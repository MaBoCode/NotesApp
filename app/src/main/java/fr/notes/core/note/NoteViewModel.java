package fr.notes.core.note;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import java.util.List;

import fr.notes.core.note.repositories.NoteRepository;
import fr.notes.utils.Logs;

public class NoteViewModel extends ViewModel {

    protected NoteRepository noteRepository;

    protected Long userId = 1L;

    protected MutableLiveData<List<Note>> notes;

    @ViewModelInject
    public NoteViewModel(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
        notes = noteRepository.fetchNotes(userId);
    }

    public void loadNotes() {
        MutableLiveData<List<Note>> fetchedNotes = noteRepository.fetchNotes(userId);
        notes.setValue(fetchedNotes.getValue());
    }

    public void saveNote(String title, String content) {
        List<Note> rawNotes = notes.getValue();
        Note note = noteRepository.saveNote(userId, title, content).getValue();

        if (rawNotes != null && note != null) {
            rawNotes.add(note);
            notes.setValue(rawNotes);
        }
    }

    public void editNote(Long noteId, String title, String content) {
        noteRepository.editNote(userId, noteId, title, content);
        loadNotes();
    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }

}

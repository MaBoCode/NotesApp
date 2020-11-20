package fr.notes.core.note;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import fr.notes.core.note.repositories.NoteRepository;

public class NoteViewModel extends ViewModel {

    protected NoteRepository noteRepository;

    protected MutableLiveData<List<Note>> notes;

    @ViewModelInject
    public NoteViewModel(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }
}

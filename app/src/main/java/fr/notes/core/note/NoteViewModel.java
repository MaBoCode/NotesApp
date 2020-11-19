package fr.notes.core.note;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class NoteViewModel extends ViewModel {

    protected MutableLiveData<List<Note>> notes;

    public LiveData<List<Note>> getNotes() {
        return notes;
    }
}

package fr.notes.core.note;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import java.util.List;

import fr.notes.core.events.BaseStateEvent;
import fr.notes.core.events.GetNotesStateEvent;
import fr.notes.core.note.repositories.NoteRepository;
import fr.notes.core.util.DataState;

public class NoteViewModel extends ViewModel {

    protected Long userId = 1L;

    private final NoteRepository noteRepository;
    private final SavedStateHandle savedStateHandle;

    protected MutableLiveData<DataState<List<Note>>> dataState = new MutableLiveData<>();

    @ViewModelInject
    public NoteViewModel(NoteRepository noteRepository, @Assisted SavedStateHandle savedStateHandle) {
        this.noteRepository = noteRepository;
        this.savedStateHandle = savedStateHandle;
    }

    public void setStateEvent(BaseStateEvent event) {
        if (event instanceof GetNotesStateEvent) {
            DataState<List<Note>> newDataState = noteRepository.fetchNotes(userId);
            dataState.setValue(newDataState);
        }
    }

    /*
    @Subscribe
    public void getNotes(GetNotesStateEvent event) {
        DataState<List<Note>> newDataState = noteRepository.fetchNotes(userId);
        dataState.setValue(newDataState);
    }

     */

    public MutableLiveData<DataState<List<Note>>> getDataState() {
        return dataState;
    }
}

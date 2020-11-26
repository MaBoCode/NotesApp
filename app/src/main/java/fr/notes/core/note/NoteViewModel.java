package fr.notes.core.note;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.squareup.otto.Subscribe;

import java.util.List;

import fr.notes.core.events.AddNoteStateEvent;
import fr.notes.core.events.EditNoteStateEvent;
import fr.notes.core.events.FetchNotesStateEvent;
import fr.notes.core.note.repositories.NoteRepository;
import fr.notes.core.util.DataState;
import fr.notes.injects.base.BaseViewModel;

public class NoteViewModel extends BaseViewModel {

    protected Long userId = 1L;

    private final NoteRepository noteRepository;
    private final SavedStateHandle savedStateHandle;

    protected MutableLiveData<DataState<List<Note>>> dataState = new MutableLiveData<>();

    @ViewModelInject
    public NoteViewModel(NoteRepository noteRepository, @Assisted SavedStateHandle savedStateHandle) {
        this.noteRepository = noteRepository;
        this.savedStateHandle = savedStateHandle;
    }

    @Subscribe
    public void fetchNotes(FetchNotesStateEvent event) {
        noteRepository.fetchNotes(userId, new NoteRepository.NoteRepositoryCallback<List<Note>>() {
            @Override
            public void onComplete(DataState<List<Note>> result) {
                dataState.postValue(result);
            }
        });
    }

    @Subscribe
    public void editNote(EditNoteStateEvent event) {
        NoteRequest request = new NoteRequest();
        request.title = event.title;
        request.content = event.content;
        noteRepository.editNote(userId, event.noteId, request, new NoteRepository.NoteRepositoryCallback<List<Note>>() {
            @Override
            public void onComplete(DataState<List<Note>> result) {
                dataState.postValue(result);
            }
        });
    }

    @Subscribe
    public void addNote(AddNoteStateEvent event) {
        noteRepository.addNote(userId, event.title, event.content, new NoteRepository.NoteRepositoryCallback<List<Note>>() {
            @Override
            public void onComplete(DataState<List<Note>> result) {
                dataState.postValue(result);
            }
        });
    }

    public MutableLiveData<DataState<List<Note>>> getDataState() {
        return dataState;
    }
}

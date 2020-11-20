package fr.notes.core.note.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.notes.core.note.Note;
import fr.notes.core.note.NoteRequest;
import fr.notes.injects.base.BaseService;
import fr.notes.injects.bus.AppBus;
import fr.notes.utils.Logs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Singleton
public class NoteClientRetrofit extends BaseService implements NoteClient {

    private AppBus bus;
    private NoteService noteService;

    public NoteClientRetrofit(AppBus bus, Retrofit retrofit) {
        this.bus = bus;
        this.noteService = retrofit.create(NoteService.class);
    }

    @Override
    public void loadNotes(Long userId, NoteClientCallback<List<Note>> callback) {
        Call<List<Note>> notes = noteService.getNotes(userId);

        notes.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                callback.success(response.body());
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                callback.failure(t);
            }
        });
    }

    @Override
    public void saveNote(Long userId, String title, String content, NoteClientCallback<Note> callback) {
        NoteRequest request = new NoteRequest();
        request.title = title;
        request.content = content;

        noteService.addNote(userId, request).enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                callback.success(response.body());
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                callback.failure(t);
            }
        });
    }

    @Override
    public void editNote(Long userId, Long noteId, String title, String content, NoteClientCallback<Note> callback) {
        NoteRequest request = new NoteRequest();
        request.title = title;
        request.content = content;

        noteService.updateNote(userId, noteId, request).enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                callback.success(response.body());
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
                callback.failure(t);
            }
        });
    }
}

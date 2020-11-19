package fr.notes.core.note.webservices;

import android.content.Context;

import java.util.List;

import fr.notes.core.note.Note;
import fr.notes.injects.bus.AppBus;
import fr.notes.utils.Logs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NoteClientRetrofit implements NoteClient {

    private AppBus bus;
    private Retrofit retrofit;
    private Context context;
    private NoteService noteService;

    public NoteClientRetrofit(AppBus bus, Retrofit retrofit, Context context) {
        this.bus = bus;
        this.retrofit = retrofit;
        this.noteService = retrofit.create(NoteService.class);
        this.context = context;
    }

    @Override
    public void loadNotes(NoteClientCallback<List<Note>> callback) {
        long userId = 1;
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
}

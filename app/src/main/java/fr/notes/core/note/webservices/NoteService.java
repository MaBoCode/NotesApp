package fr.notes.core.note.webservices;

import java.util.List;

import fr.notes.core.note.Note;
import fr.notes.core.note.NoteRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NoteService {

    @GET("users/{userId}/notes")
    Call<List<Note>> getNotes(
            @Path("userId") Long userId
    );

    @POST("users/{userId}/notes")
    Call<Note> addNote(
            @Path("userId") Long userId,
            @Body NoteRequest request
    );

    @PUT("users/{userId}/notes/{noteId}")
    Call<Note> updateNote(
            @Path("userId") Long userId,
            @Path("noteId") Long noteId,
            @Body NoteRequest request
    );
}

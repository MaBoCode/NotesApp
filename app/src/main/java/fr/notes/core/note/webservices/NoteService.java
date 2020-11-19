package fr.notes.core.note.webservices;

import java.util.List;

import fr.notes.core.note.Note;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NoteService {

    @GET("users/{userId}/notes")
    Call<List<Note>> getNotes(
            @Path("userId") long userId
    );

    @POST("users/{userId}/notes")
    Call<Note> addNote(
            @Path("userId") long userId
    );

    @PUT("users/{userId}/notes/{noteId}")
    Call<Note> updateNote(
            @Path("userId") long userId,
            @Path("noteId") long noteId
    );
}
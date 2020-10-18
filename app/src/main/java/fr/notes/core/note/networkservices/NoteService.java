package fr.notes.core.note.networkservices;

import fr.notes.core.note.NoteRequest;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface NoteService {

    @GET()
    Call<ResponseBody> saveNote(
            @Body NoteRequest payload
    );
}

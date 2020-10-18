package fr.notes.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.notes.core.note.networkservices.NoteClient;
import fr.notes.core.note.networkservices.NoteClientRetrofit;
import fr.notes.injects.bus.AppBus;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class NetworkModule {

    private Context context;

    public NetworkModule(Context context) {
        this.context = context;
    }

    private Retrofit createRetrofit(OkHttpClient okHttpClient) {
        String serverUrl = "";

        return new Retrofit.Builder()
                .baseUrl(serverUrl)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public NoteClient provideNoteClient(AppBus bus) {
        return new NoteClientRetrofit(bus, context);
    }

}

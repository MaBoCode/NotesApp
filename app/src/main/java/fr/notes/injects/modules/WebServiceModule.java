package fr.notes.injects.modules;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import fr.notes.core.note.webservices.NoteClient;
import fr.notes.core.note.webservices.NoteClientRetrofit;
import fr.notes.injects.bus.AppBus;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class WebServiceModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        String serverUrl = "http://192.168.5.18:3000/api/";

        return new Retrofit.Builder()
                .baseUrl(serverUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public NoteClient provideNoteClient(AppBus bus, Retrofit retrofit) {
        return new NoteClientRetrofit(bus, retrofit);
    }
}

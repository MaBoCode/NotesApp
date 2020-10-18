package fr.notes.core.note.networkservices;

import android.content.Context;

import fr.notes.injects.bus.AppBus;

public class NoteClientRetrofit implements NoteClient {

    private AppBus bus;

    private Context context;

    private NoteService noteService;

    public NoteClientRetrofit(AppBus bus, Context context) {
        this.bus = bus;
        this.context = context;
    }

    @Override
    public void save() {

    }
}

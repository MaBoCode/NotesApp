package fr.notes.injects;

import javax.inject.Singleton;

import dagger.Component;
import fr.notes.App;
import fr.notes.injects.base.BaseFragment;
import fr.notes.injects.base.BaseFrameLayout;
import fr.notes.injects.modules.MainModule;
import fr.notes.views.MainActivity;
import fr.notes.views.MainFragment;
import fr.notes.views.notes.NoteCardView;
import fr.notes.views.notes.NoteDetailsFragment;
import fr.notes.views.notes.NotesListView;

@Singleton
@Component(modules = MainModule.class)
public interface AppComponent {

    void inject(App injectable);

    // Base
    void inject(BaseFragment injectable);

    // Activities
    void inject(MainActivity injectable);

    // Fragments
    void inject(MainFragment injectable);
    void inject(NoteDetailsFragment injectable);

    // Views
    void inject(NoteCardView injectable);
    void inject(NotesListView injectable);
}

package fr.notes.core.note.util;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import fr.notes.core.note.Note;
import fr.notes.core.note.webservices.NoteEntity;
import fr.notes.core.util.EntityMapper;

public class NoteEntityMapper implements EntityMapper<NoteEntity, Note> {

    @Inject
    public NoteEntityMapper() {

    }

    @Override
    public Note mapFromEntity(NoteEntity entity) {
        return new Note(
                entity.id,
                entity.title,
                entity.content,
                entity.dateAndTime
        );
    }

    @Override
    public NoteEntity mapToEntity(Note model) {
        return new NoteEntity(
                model.id,
                model.title,
                model.content,
                model.dateAndTime
        );
    }

    public List<Note> mapFromEntities(List<NoteEntity> entities) {
        List<Note> notes = new ArrayList<>();
        for (NoteEntity entity: entities) {
            Note note = mapFromEntity(entity);
            notes.add(note);
        }
        return notes;
    }
}

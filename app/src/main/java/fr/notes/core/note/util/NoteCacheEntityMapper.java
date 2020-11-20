package fr.notes.core.note.util;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import fr.notes.core.note.Note;
import fr.notes.core.note.cache.NoteCacheEntity;
import fr.notes.core.util.EntityMapper;

public class NoteCacheEntityMapper implements EntityMapper<NoteCacheEntity, Note> {

    @Inject
    public NoteCacheEntityMapper() {
    }

    @Override
    public Note mapFromEntity(NoteCacheEntity entity) {
        return new Note(
                entity.id,
                entity.title,
                entity.content
        );
    }

    @Override
    public NoteCacheEntity mapToEntity(Note model) {
        return new NoteCacheEntity(
                model.id,
                model.title,
                model.content
        );
    }

    public List<Note> mapFromEntities(List<NoteCacheEntity> entities) {
        List<Note> notes = new ArrayList<>();
        for (NoteCacheEntity entity: entities) {
            Note note = mapFromEntity(entity);
            notes.add(note);
        }
        return notes;
    }
}

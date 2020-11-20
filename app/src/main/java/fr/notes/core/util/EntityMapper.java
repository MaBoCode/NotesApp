package fr.notes.core.util;

public interface EntityMapper<E, M> {

    M mapFromEntity(E entity);

    E mapToEntity(M model);
}

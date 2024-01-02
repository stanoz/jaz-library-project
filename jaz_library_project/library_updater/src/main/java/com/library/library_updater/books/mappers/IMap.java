package com.library.library_updater.books.mappers;

public interface IMap<Dto, Entity> {
    Entity mapToEntity(Dto dto);
}

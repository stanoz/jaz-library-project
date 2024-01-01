package com.library.library_updater.mappers;

public interface IMap<Dto, Entity> {
    Entity mapToEntity(Dto dto);
}

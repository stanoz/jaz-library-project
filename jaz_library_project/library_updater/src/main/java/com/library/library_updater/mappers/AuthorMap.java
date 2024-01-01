package com.library.library_updater.mappers;

import com.library.library_client.contract.AuthorDto;
import com.library.library_data.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMap implements IMap<AuthorDto, Author>{
    @Override
    public Author mapToEntity(AuthorDto authorDto) {
        Author authorEntity = new Author();
        authorEntity.setName(authorDto.getName());
        authorEntity.setYearOfBirth(authorDto.getYearOfBirth());
        authorEntity.setYearOfDeath(authorDto.getYearOfDeath());
        return authorEntity;
    }
}

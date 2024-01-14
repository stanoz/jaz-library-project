package com.library.library_web_api.webapi.validators;

import com.library.library_client.contract.AuthorDto;
import com.library.library_client.contract.BookDto;
import com.library.library_web_api.webapi.contract.NewBookDto;
import org.springframework.stereotype.Component;

@Component
public class Validator implements IValidate{
    @Override
    public boolean validateBook(BookDto bookDto) {
        return (!bookDto.getTitle().isBlank() && bookDto.getDownloadCount() < 0);
    }

    @Override
    public boolean validateNewBook(NewBookDto newBookDto) {
        return (!newBookDto.getTitle().isBlank() && newBookDto.getDownloadCount() < 0);
    }

    @Override
    public boolean validateAuthor(AuthorDto authorDto) {
        if (authorDto.getName().isBlank()){
            return false;
        }
        if (authorDto.getYearOfBirth() < 0 && authorDto.getYearOfDeath() <= 0){
            return authorDto.getYearOfDeath() > authorDto.getYearOfBirth();
        }
        if (authorDto.getYearOfBirth() >= 0 && authorDto.getYearOfDeath() > 0){
            return authorDto.getYearOfDeath() < authorDto.getYearOfBirth();
        }
        return true;
    }

    @Override
    public boolean validateStringDto(String dtoNameField) {
        return !dtoNameField.isBlank();
    }
}

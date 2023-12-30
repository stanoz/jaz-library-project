package com.library.library_client;

import com.library.library_client.contract.ResultsDto;

public interface IBooksClient {
    ResultsDto getBooks();
    ResultsDto getBooks(int page);
}

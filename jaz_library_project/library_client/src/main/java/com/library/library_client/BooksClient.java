package com.library.library_client;

import com.library.library_client.contract.ResultsDto;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Component
@Data
public class BooksClient implements IBooksClient{
    RestTemplate restTemplate;
    String baseUrl;
    private final IBooksClientSettings _settings;

    public BooksClient(IBooksClientSettings settings) {
        restTemplate =new RestTemplate();
        this.baseUrl = settings.getBaseUrl();
        _settings = settings;
    }

    @Override
    public ResultsDto getBooks() {
        return getBooks(1);
    }

    @Override
    public ResultsDto getBooks(int page) {
        var url = getBooksUriBuilder()
                .queryParam("page", page)
                .build()
                .toUriString();
        return restTemplate.getForObject(url, ResultsDto.class);
    }
    private UriComponentsBuilder getBooksUriBuilder(){
        return _settings.getUrlBuilder()
                .pathSegment("books");
    }
}

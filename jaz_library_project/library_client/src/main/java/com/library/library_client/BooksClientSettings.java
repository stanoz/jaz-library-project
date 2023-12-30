package com.library.library_client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BooksClientSettings implements IBooksClientSettings{
    @Value("gutenberg.api.host")
    private String baseUrl;

    public BooksClientSettings(
            @Value("gutenberg.api.host") String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }
}

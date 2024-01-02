package com.library.library_client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BooksClientSettings implements IBooksClientSettings{
    @Value("gutendex.com")
    private String baseUrl = "gutendex.com";


    @Override
    public String getBaseUrl() {
        return baseUrl;
    }
}

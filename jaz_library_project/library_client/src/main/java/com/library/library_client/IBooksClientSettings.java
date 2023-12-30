package com.library.library_client;

import org.springframework.web.util.UriComponentsBuilder;

public interface IBooksClientSettings {
    String getBaseUrl();
    default UriComponentsBuilder getUrlBuilder(){
        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(getBaseUrl());
    }
}

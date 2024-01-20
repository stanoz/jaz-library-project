package com.library.library_web_api.webapi.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NewBookDto {
    @JsonProperty("title")
    private String title;
    @JsonProperty("download_count")
    private int downloadCount;
}

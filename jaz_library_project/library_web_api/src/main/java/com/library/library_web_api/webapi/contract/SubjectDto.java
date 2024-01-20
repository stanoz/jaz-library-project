package com.library.library_web_api.webapi.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SubjectDto {
    private Long id;
    @JsonProperty("name")
    private String name;
}

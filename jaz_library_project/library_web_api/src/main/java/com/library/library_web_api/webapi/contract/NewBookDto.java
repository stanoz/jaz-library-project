package com.library.library_web_api.webapi.contract;

import lombok.Data;

@Data
public class NewBookDto {
    private String title;
    private int downloadCount;
}

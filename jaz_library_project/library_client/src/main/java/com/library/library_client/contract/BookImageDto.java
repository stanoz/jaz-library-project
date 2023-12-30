package com.library.library_client.contract;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookImageDto {
    @JsonProperty("image/jpeg")
    private String imageUrl;
}

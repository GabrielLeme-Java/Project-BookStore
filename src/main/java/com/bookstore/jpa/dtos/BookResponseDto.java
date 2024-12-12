package com.bookstore.jpa.dtos;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class BookResponseDto {

    private String title;
    private UUID publisherId;
    private Set<AuthorDto> authors;
    private String reviewComment;

}

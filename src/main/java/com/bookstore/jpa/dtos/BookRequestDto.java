package com.bookstore.jpa.dtos;

import lombok.*;
import java.util.Set;
import java.util.UUID;

@Data
public class BookRequestDto {

    private String title;
    private UUID publisherId;
    private Set<UUID> authorIds;
    private String reviewComment;

}

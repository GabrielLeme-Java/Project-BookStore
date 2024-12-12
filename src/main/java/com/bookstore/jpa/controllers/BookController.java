package com.bookstore.jpa.controllers;

import com.bookstore.jpa.dtos.BookRequestDto;
import com.bookstore.jpa.dtos.BookResponseDto;
import com.bookstore.jpa.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookstore/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDto> save(@RequestBody BookRequestDto bookRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.allBooks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        this.bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}

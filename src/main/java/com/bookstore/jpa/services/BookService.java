package com.bookstore.jpa.services;

import com.bookstore.jpa.dtos.BookRecordDto;
import com.bookstore.jpa.models.BookModel;
import com.bookstore.jpa.models.ReviewModel;
import com.bookstore.jpa.repositories.AuthorRepository;
import com.bookstore.jpa.repositories.BookRepository;
import com.bookstore.jpa.repositories.PublisherRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;


    @Transactional //Garante um RollBack em caso de erros, por haver varias transações com a base da dados.
    public BookModel saveBook(BookRecordDto bookRecordDto){
        BookModel book = new BookModel();
        book.setTitle(bookRecordDto.title());
        book.setPublisher(publisherRepository.findById(bookRecordDto.publisherId()).get());
        book.setAuthors(authorRepository.findAllById(bookRecordDto.auhtorIds()).stream().collect(Collectors.toSet()));

        ReviewModel reviewModel = new ReviewModel();
        reviewModel.setComment(bookRecordDto.reviewComment());
        reviewModel.setBook(book);
        book.setReview(reviewModel);

        return bookRepository.save(book);
    }
}
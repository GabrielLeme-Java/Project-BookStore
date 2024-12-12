package com.bookstore.jpa.services;

import com.bookstore.jpa.dtos.BookRequestDto;
import com.bookstore.jpa.dtos.BookResponseDto;
import com.bookstore.jpa.models.AuthorModel;
import com.bookstore.jpa.models.BookModel;
import com.bookstore.jpa.models.PublisherModel;
import com.bookstore.jpa.models.ReviewModel;
import com.bookstore.jpa.repositories.AuthorRepository;
import com.bookstore.jpa.repositories.BookRepository;
import com.bookstore.jpa.repositories.PublisherRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final ModelMapper modelMapper;

    public List<BookResponseDto> allBooks() {
//return this.bookRepository.findAll().stream().map(b -> this.modelMapper.map(b, BookResponseDto.class)).toList();
        List<BookModel> listReturn = this.bookRepository.findAll();
        List<BookResponseDto> listDtoReturn = new ArrayList<>();

        for (BookModel b : listReturn) {
            listDtoReturn.add(this.modelMapper.map(b, BookResponseDto.class));
        }
        return listDtoReturn;
    }


    @NotNull
    @Transactional //Garante um RollBack em caso de erros, por haver varias transações com a base da dados.
    public BookResponseDto saveBook(BookRequestDto bookRequestDto) {
        BookModel book = new BookModel();

        BookModel map = this.modelMapper.map(bookRequestDto, BookModel.class);
        if (map.getTitle().isEmpty()) {
            throw new RuntimeException("Necessário um Título.");
        }
        book.setTitle(map.getTitle());

        Optional<PublisherModel> byId = publisherRepository.findById(bookRequestDto.getPublisherId());
        if (byId.isEmpty()) {
            throw new RuntimeException("Editora não cadastrada.");
        }
        book.setPublisher(byId.get());

        List<AuthorModel> allById = authorRepository.findAllById(bookRequestDto.getAuthorIds());
        if (allById.isEmpty()) {
            throw new RuntimeException("Authores não cadastrados.");
        }
        book.setAuthors(new HashSet<>(allById));

        ReviewModel reviewModel = new ReviewModel();
        if (bookRequestDto.getReviewComment().isEmpty()) {
            throw new RuntimeException("Precisa escrever um resumo do livro.");
        }
        reviewModel.setComment(bookRequestDto.getReviewComment());
        reviewModel.setBook(book);
        book.setReview(reviewModel);

        return this.modelMapper.map(this.bookRepository.save(book), BookResponseDto.class);
    }


    @Transactional
    public void deleteBook(UUID id) {
        this.bookRepository.deleteById(id);
    }
}

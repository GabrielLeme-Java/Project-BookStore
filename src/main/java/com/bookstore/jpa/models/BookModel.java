package com.bookstore.jpa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_BOOK")
@Getter
@Setter
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true) //Não pode ficar sem titulo, não pode ter titulos repetidos.
    private String title;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private PublisherModel publisher;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_book_author", //Tabela auxiliar para relacionamento.
            joinColumns = @JoinColumn(name = "book_id"), //Chave estrangeira book.
            inverseJoinColumns = @JoinColumn(name = "author_id")) //Chave estrangeira auhtor.
    private Set<AuthorModel> authors = new HashSet<>();

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
    private ReviewModel review;
}

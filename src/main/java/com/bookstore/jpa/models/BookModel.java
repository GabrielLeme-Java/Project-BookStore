package com.bookstore.jpa.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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


    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private PublisherModel publisher;
}

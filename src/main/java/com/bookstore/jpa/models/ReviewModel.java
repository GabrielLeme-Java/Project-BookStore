package com.bookstore.jpa.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "TB_REVIEW")
@Getter
@Setter
public class ReviewModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false) //Tem que existir um breve resumo do livro,
    private String comment;
}

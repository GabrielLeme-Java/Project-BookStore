package com.bookstore.jpa.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "TB_AUTHOR")
@Getter
@Setter
public class AuthorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true) //Não pode ficar sem nome, não pode ter autores com nomes iguais.
    private String name;
}

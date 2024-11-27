package com.bookstore.jpa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //Apenas permissão de escrita e não de leitura.
    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY) //Para não precisar buscar a coleção de livros e cada author.
    private Set<BookModel> books =new HashSet<>();
}

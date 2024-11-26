package com.bookstore.jpa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_PUBLISHER")
@Getter
@Setter
public class PublisherModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true) //Não pode ficar sem nome, não pode ter editoras com nomes iguais.
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //(Apenas como escrita)Para garantir que não vai dar erros de serializações já que estamos usando consulta lenta "LAZY"
    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY) //LAZY-Carregamento lento, trazer apenas o essêncial.
    private Set<BookModel> books = new HashSet<>();
}

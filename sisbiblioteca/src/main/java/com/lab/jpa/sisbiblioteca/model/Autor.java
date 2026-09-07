package com.lab.jpa.sisbiblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

// Uma entidade representa uma tabela; cada objeto salvo representa uma linha.
@Entity
@Table(name = "autores")
@Data // Lombok gera getters, setters, equals, hashCode e toString.
@NoArgsConstructor // O JPA precisa de um construtor sem argumentos.
@AllArgsConstructor
public class Autor {

    @Id // Chave primária: identifica unicamente o autor.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O banco gera o ID.
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    // Um autor possui vários livros. "autor" é o campo correspondente em Livro.
    // ALL propaga operações aos livros; LAZY carrega a coleção quando acessada.
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude // Evita percorrer autor -> livros -> autor ao imprimir o objeto.
    private List<Livro> livros = new ArrayList<>();

    // Ao cadastrar, informamos apenas o nome; o banco cuidará do ID.
    public Autor(String nome) {
        this.nome = nome;
    }
}

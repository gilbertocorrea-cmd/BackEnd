package com.lab.jpa.sisbiblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "livros")
@Data // Gera os métodos de acesso usados pelo menu, como getTitulo().
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(name = "ano_publicacao") // Nome da coluna no banco de dados.
    private Integer anoPublicacao;

    // Vários livros podem pertencer ao mesmo autor.
    // EAGER traz também o autor, permitindo mostrar seu nome na listagem.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id", nullable = false) // Chave estrangeira obrigatória.
    private Autor autor;

    public Livro(String titulo, Integer anoPublicacao, Autor autor) {
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.autor = autor;
    }
}

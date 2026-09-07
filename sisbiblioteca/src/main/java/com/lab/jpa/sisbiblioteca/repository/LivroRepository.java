package com.lab.jpa.sisbiblioteca.repository;

import com.lab.jpa.sisbiblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    // Busca por parte do título, sem diferenciar maiúsculas e minúsculas.
    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    // Percorre a associação Livro.autor e compara o ID desse autor.
    List<Livro> findByAutorId(Long autorId);
}

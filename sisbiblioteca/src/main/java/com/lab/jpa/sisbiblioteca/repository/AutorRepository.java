package com.lab.jpa.sisbiblioteca.repository;

import com.lab.jpa.sisbiblioteca.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// JpaRepository já fornece save, findAll, findById e deleteById.
// Autor é a entidade administrada; Long é o tipo de sua chave primária.
public interface AutorRepository extends JpaRepository<Autor, Long> {
    // O Spring monta a consulta pelo nome: busca parte do nome sem distinguir maiúsculas.
    List<Autor> findByNomeContainingIgnoreCase(String nome);
}

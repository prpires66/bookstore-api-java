package dev.prpires66.service;

import dev.prpires66.domain.model.Livro;

import java.util.List;

public interface LivroService {
    Livro findById(Long id);
    List<Livro> findAll();
    Livro create(Livro livroToCreate);
    void delete(Long id);
    Livro update(Long id, Livro livroToUpdate);

}

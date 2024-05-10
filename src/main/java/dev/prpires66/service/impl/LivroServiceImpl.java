package dev.prpires66.service.impl;

import dev.prpires66.domain.model.Livro;
import dev.prpires66.domain.repository.LivroRepository;
import dev.prpires66.service.LivroService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository;

    public LivroServiceImpl(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @Override
    public Livro findById(Long id) {
        return livroRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    @Override
    public Livro create(Livro livroToCreate) {
        if (livroRepository.existsByIdLivro(livroToCreate.getIdLivro())) {
            throw new IllegalArgumentException("This Account number already exists.");
        }
        return livroRepository.save(livroToCreate);
    }

    @Override
    public void delete(Long id) {
        livroRepository.delete(this.findById(id));
    }

    @Override
    public Livro update(Long id, Livro livroToUpdate) {
        Livro dbLivro = this.findById(id);
        dbLivro.setAutor(livroToUpdate.getAutor());
        dbLivro.setPreco(livroToUpdate.getPreco());
        dbLivro.setTitulo(livroToUpdate.getTitulo());
        dbLivro.setLinkImagem(livroToUpdate.getLinkImagem());

        return this.livroRepository.save(dbLivro);
    }
}
package dev.prpires66.controller;

import dev.prpires66.domain.model.Livro;
import dev.prpires66.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public ResponseEntity<List<Livro>> findAll() {
        var livros = livroService.findAll();
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Long id) {
        var livro = livroService.findById(id);
        return ResponseEntity.ok(livro);
    }

    @PostMapping
    public ResponseEntity<Livro> create(@RequestBody Livro livroToCreate) {
        var livroCreated = livroService.create(livroToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(livroCreated.getIdLivro())
                .toUri();
        return ResponseEntity.created(location).body(livroCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> update(@PathVariable Long id,  @RequestBody Livro livroToUpdate) {
        var livro = this.livroService.update(id, livroToUpdate);
        return ResponseEntity.ok(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Livro> delete(@PathVariable Long id) {
        var livro = this.livroService.findById(id);
        this.livroService.delete(id);
        return ResponseEntity.ok(livro);
    }

}

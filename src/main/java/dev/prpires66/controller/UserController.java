package dev.prpires66.controller;

import dev.prpires66.domain.model.Funcionario;
import dev.prpires66.service.FuncionarioService;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class UserController {

    private final FuncionarioService funcionarioService;

    public UserController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> findAll() {
        var funcionarios = funcionarioService.findAll();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable Long id) {
        var funcionario = funcionarioService.findById(id);
        return ResponseEntity.ok(funcionario);
    }

    @PostMapping
    public ResponseEntity<Funcionario> create(@RequestBody Funcionario funcionarioToCreate) {
        var funcionarioCreated = funcionarioService.create(funcionarioToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(funcionarioCreated.getIdFuncionario())
                .toUri();
        return ResponseEntity.created(location).body(funcionarioCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> update(@PathVariable Long id,  @RequestBody Funcionario funcionarioToUpdate) {
        var funcionario = this.funcionarioService.update(id, funcionarioToUpdate);
        return ResponseEntity.ok(funcionario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Funcionario> delete(@PathVariable Long id) {
        var funcionario = this.funcionarioService.findById(id);
        this.funcionarioService.delete(id);
        return ResponseEntity.ok(funcionario);
    }

}

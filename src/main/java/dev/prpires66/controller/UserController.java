package dev.prpires66.controller;

import dev.prpires66.domain.model.Funcionario;
import dev.prpires66.service.UserService;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> findAll() {
        var users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable Long id) {
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Funcionario> create(@RequestBody Funcionario userToCreate) {
        var userCreated = userService.create(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getIdFuncionario())
                .toUri();
        return ResponseEntity.created(location).body(userCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> update(@PathVariable Long id,  @RequestBody Funcionario userToUpdate) {
        var user = this.userService.update(id, userToUpdate);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Funcionario> delete(@PathVariable Long id) {
        var user = this.userService.findById(id);
        this.userService.delete(id);
        return ResponseEntity.ok(user);
    }

}
